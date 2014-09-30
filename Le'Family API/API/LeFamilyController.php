<?php
header('Content-Type: application/json');
require_once 'LeFamilyDB.php';

error_log("Test");
$LFC = new LeFamilyController();

$functionCall = $_POST['functionCall'];

switch ($functionCall)
{
	//
	case '1':
		$phoneNumber = $_POST['phoneNumber'];
		$returnValue = $LFC->check_for_existing_user($phoneNumber);
		echo json_encode($returnValue );
		break;
}
class LeFamilyController
{
	public $LFDB;
	function __construct()
	{
		$this->LFDB = new LeFamilyDB();
	}

	/**
	 * retrieve userid with phone number
	 * helper function
	 * @param int $phoneNumber
	 * @return userID or false:
	 */
	function retrieve_user_ID($phoneNumber)
	{
		$response = $this->LFDB->select_userID_from_userPhone($phoneNumber);
		if($response->success)
		{
			$userID = $response->message[0];
			return $userID;
		}
		else
		{
			return $response->success;
		}
	}
	/**
	 * Check for existing user in loading page
	 * @param int $phoneNumber
	 * @return string
	 */
	function check_for_existing_user($phoneNumber)
	{
		$response = $this->LFDB->select_userID_from_userPhone($phoneNumber);
		if($response->success == false)
			$response = $this->LFDB->create_user($phoneNumber);
		return $response;
	}
	/**
	 * Create user
	 * @param array $user_details
	 * 	-name
	 * 	-phoneNumber
	 * 	-surname
	 * @return string
	 */

	function create_user($userDetails)
	{
		$userPhoneNumber = $userDetails['phoneNumber'];
		$response = $this->LFDB->insert_user( $userPhoneNumber);
		return $response;
	}

	/**
	 * 
	 * @param int $phoneNumber
	 */
	function retrieve_pet_info($phoneNumber)
	{
		$userID = $this->retrieve_user_ID($phoneNumber);
	}
	
	/**
	 * Create Family
	 * @param array $familyDetails
	 * 	-phoneNumber
	 * 	-familyName
	 * @return string
	 */
	function create_family($familyDetails)
	{
		$userID = $this->retrieve_user_ID($familyDetails['phoneNumber']);	
		if($userID != false)
		{
			$familyName = $familyDetails['familyName'];
			$userName= $familyDetails['userName'];
			$userPosition = $familyDetails['$userPosition'];
			$response = $this->LFDB->insert_family($familyName, $userID);
			if($response->success == true)
			{
				$response = $this->LFDB->select_familyID($familyName, $userID);
				$familyID = $response->message[0];
				$response = $this->LFDB->insert_family_user($userID, $familyID, $userPosition, $userName, true); //admin table 
				
				return json_encode($response);
			}
			else
			{
				return json_encode("There exist such a family");
			}
		}
		else 
		{
			return json_encode("There is no such user");
		}
		
	}
	
	/**
	 * Create family member
	 * Needs to include notification
	 * @param array $familyUserDetails
	 *  -phoneNumber
	 *  -familyID
	 *  -position
	 * @return string
	 */
	function create_family_member($familyUserDetails)
	{
		$userID = $this->retrieve_user_ID($familyUserDetails['phoneNumber']);
		$userName= $familyDetails['userName'];
		$userPosition = $familyDetails['$userPosition'];
		if($userID != false)
		{
			//send notification to invitee
			$response = $this->LFDB->insert_family_user($userID, $familyID, $userPosition, $userName, false);
			return json_encode($response);
		}
		else
		{
			return json_encode("There is no such user");
		}
	}
	
	/**
	 * Get all members of family by familyid
	 * 
	 * @param int $familyID
	 * @return string
	 */
	function retrieve_family_member($familyID)
	{
		
		$responseFamily = $this->LFDB->select_family_member_by_family_id($familyID);
		$familyMember = $responseFamily->message;
		$allMember = array();
		$max = sizeof($familyMember);
		$count = 0;
		while($count < $max)
		{
			foreach($familyMember[$count] as $key => $value)
			{
				$responseUser = $this->LFDB->select_user($value);
				$allMember[$count] = $responseUser;				
			}
			$count++;
		}
		//return standard - this is just an array
		return json_encode($allMember);
	}
	
	/**
	 * Delete user from family 
	 * @param array $familyUserDetails
	 *  -phoneNumber of user to remove
	 *  -familyID
	 * @return string
	 */
	function remove_family_member($familyUserDetails)
	{
		$userID = $this->retrieve_user_ID($familyUserDetails['phoneNumber']);
		if($userID != false)
		{
			//send notification to invitee
			$response = $this->LFDB->delete_family_member($userID, $familyUserDetails['familyID']);
			return $response->success;
		}
		else
		{
			return json_encode("There is no such user");
		}
	}
	
	/**
	 * not tested
	 * check if user is admin, used concurrently for remove_user() function
	 * @param int $phoneNumber
	 * @return boolean
	 */
	function check_if_user_is_admin($phoneNumber)
	{
		$userID = $this->retrieve_user_ID($phoneNumber);
		$response =$this->LFDB->select_family_created_from_user_admin_id($userID);
		return $response;
	}
	/**
	 * not tested
	 * Delete user from user
	 * deletes from family_user first
	 * checks if he/she is admin of family, if true, select a new 
	 * @param int $phoneNumber
	 * @return true if succeed in removing, string if not succeeded
	 */
	function remove_user($phoneNumber)
	{
		$check = $this->check_if_user_is_admin($phoneNumber);
		if($check == false)
		{
			//send notification to invitee
			$this->LFDB->delete_family_member($userID, -1);
			$response = $this->LFDB->delete_user($userID);
			return $response->success;
		}
		else
		{
			return json_encode("You are an admin, please delete away family before deleting yourself");
		}
	}
	
	/**
	 * @param int $familyID
	 * @return true if succeed, false if fails
	 */
	function remove_family($familyID)
	{
		//send notification to invitee
		$response = $this->LFDB->delete_family($familyID);
		return $response->success;
		
	}
	
	/**
	 * Retrieve family details of user
	 * All family ID, family member of the least family ID eg 1, 5,9 then retrieve all from id 1
	 * @param int $phoneNumber
	 * @return string details of user
	 */
	function retreive_family_details($phoneNumber)
	{
		$userID = $this->retrieve_user_ID($phoneNumber);
		$responseFamilyID = $this->LFDB->select_family_id_by_user_id($userID);
		$familyDetailsArray = array();
		$familyIDArray = $responseFamilyID->message;
		$max = sizeof($familyIDArray);
		$count = 0;
		var_dump ($responseFamilyID);
		while($count < $max)
		{
			foreach($familyIDArray[$count] as $key => $value)
			{
				$responseFamilyDetails = $this->LFDB->select_family($value);
				//var_dump($responseFamilyDetails);
				$familyDetailsArray[$count] = $responseFamilyDetails->message;
			}
			$count++;
		}
		
		return json_encode($familyDetailsArray);
	
	}
	/**
	 * inserting event and creating host user to event
	 * 
	 * @param int $phoneNumber
	 * @param array $eventDetails
	 * eg for $eventDetails"type" => "dinner", "title" => "popo birthday", "date" => "2014/09/09 11:45"
	 * @return string
	 */
	function create_event($phoneNumber, $eventDetails)	
	{
		$userID = $this->retrieve_user_ID($phoneNumber);
		$eventType = $eventDetails["type"];
		$eventTitle = $eventDetails["title"];
		$eventDateTime = $eventDetails["date"];
		$responseEvent = $this->LFDB->insert_event($eventType,$eventTitle, $userID,$eventDateTime);
		if($responseEvent->success == true)
		{
			$eventID = $responseEvent->message;
			$response = $this->LFDB->insert_user_event($userID, $eventID);
		}
		return json_encode($responseEvent);
	}
	
	/**
	 * Editing of event
	 * eventid is retrieved from create event function. the function returns the event ID 
	 * @param array  $eventDetails
	 *  eg for $eventDetails "id" => "1" "type" => "dinner", "title" => "popo birthday", "date" => "2014/09/09 11:45"
	 * @return string
	 */
	function edit_event($eventDetails)
	{
		$eventID = $eventDetails["id"];
		$eventType = $eventDetails["type"];
		$eventTitle = $eventDetails["title"];
		$eventDateTime = $eventDetails["date"];
		$response = $this->LFDB->update_event($eventID, $eventType, $eventTitle, $eventDateTime);
	
		return json_encode($response);
	}
	function remove_event($eventID)
	{
		$response = $this->LFDB->delete_event($eventID);
		return json_encode($response);
	}
	
	function select_event($phoneNumber)
	{
		$counter = 0;
		$eventArray = array();
		$response = array();
		$userID = $this->retrieve_user_ID($phoneNumber);

		$responseFamilyID = $this->LFDB->select_family_id_by_user_id($userID);

		foreach( $responseFamilyID->message as $familyID)
		{
			$responseEventInfo = $this->LFDB->select_event($familyID['family_userFamilyID'] );
			if($responseEventInfo->success == true)
			{
				$eventArray[$counter] = $responseEventInfo->message;
				$counter++;
			}
		}
		$response["success"] = true;
		$response["message"] = $eventArray;
		error_log(print_r($eventArray, true));
		return json_encode($response);
	}
	function add_user_to_event($phoneNumber, $eventID)
	{
		$userID = $this->retrieve_user_ID($phoneNumber);
		
		$response = $this->LFDB->insert_user_event( $userID,$eventID);
		
		return json_encode($response);
	}
}

//$array = array("name" => "hi", "phoneNumber" => "1234568", "surname" => "ber");
// $array = array("familyName" => "berber", "phoneNumber" => "1234568", "surname" => "ber");
// $array = array("id"=> 3, "type" => "dinner", "title" => "gege birthday", "date" => "2014/09/09 12:45" );
// //var_dump($array);
// $test = new LeFamilyController();
// $response = $test->select_event(0);
// var_dump($response);
?>