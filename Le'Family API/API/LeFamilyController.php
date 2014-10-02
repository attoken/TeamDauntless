<?php
require_once 'LeFamilyDB.php';
$LFC = new LeFamilyController();
// $response = $test->retreive_family_details(0);
//$phoneNumber = $_POST['phoneNumber'];
$functionCall = $_POST['functionCall'];
switch ($functionCall)
{
	//
	case '1':
		$phoneNumber = $_POST['phoneNumber']; // variables that is passed in
		$returnValue = $LFC->check_for_existing_user($phoneNumber); // functions that use the variables
		echo json_encode($returnValue ); // take value from functions to json
		break;
	case '2':
		$familyDetails = json_decode($_POST['adminDetails'],true);	
		error_log(print_r($familyDetails,true));
		$returnValue = $LFC->create_family($familyDetails);
		echo json_encode($returnValue); // take value from functions to json
		break;
	case '3':
		$phoneNumber = $_POST['phoneNumber']; // variables that is passed in
		$returnValue = $LFC->retreive_family_details($phoneNumber); // functions that use the variables
		echo json_encode($returnValue ); // take value from functions to json
		break;
	case '4':
		$familyID = $_POST['familyID']; // variables that is passed in
		$returnValue = $LFC->retrieve_family_member($familyID); // functions that use the variables
		echo json_encode($returnValue ); // take value from functions to json
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
		$response_userid = $this->LFDB->select_userID_from_userPhone($phoneNumber);
		if($response_userid->success == false)
		{
			$response_createuser = $this->create_user($phoneNumber);
			return $response_createuser;
		}
		return $response_userid;
	}
	/**
	 * Create user
	 * @param array $user_details
	 * 	-name
	 * 	-phoneNumber
	 * 	-surname
	 * @return string
	 */
	function create_user($phoneNumber)
	{
		
		$response = $this->LFDB->insert_user($phoneNumber);
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
			$familyPostion = $familyDetails['position'];
			$userName = $familyDetails['name'];
			$admin = $familyDetails["admin"];
			$response = $this->LFDB->insert_family($familyName, $userID);
			if($response->success == true)
			{
				$response = $this->LFDB->select_familyID($familyName, $userID);
				$familyID = $response->message[0];
				$this->LFDB->insert_family_user($userID, $familyID, $familyPostion, $userName, $admin);
				return json_encode($familyID);
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
	 *  -username
	 *  -
	 * @return string
	 */
	function create_family_member($familyUserDetails)
	{
		$userID = $this->retrieve_user_ID($familyUserDetails['phoneNumber']);
		if($userID != false)
		{
			//send notification to invitee
			$familyName = $familyDetails['familyName'];
			$familyPostion = $familyDetails['position'];
			$userName = $familyDetails['name'];
			$admin = $familyDetails["admin"];
			$this->LFDB->insert_family_user($userID, $familyUserDetails['familyID'], $familyUserDetails['position']);
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
				$responseUser = $this->LFDB->select_user($value, $familyID);
				$allMember[$count] = $responseUser->message;				
			}
			$count++;
		}
		return $allMember;
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
		$response = select_family_created_from_user_admin_id($userID);
		if($response->success != false)
			return true;
		else
			return false;
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
				
				$familyDetailsArray[$count] = $responseFamilyDetails->message;
			}
			$count++;
		}
		error_log(print_r($familyDetailsArray, true));
		return $familyDetailsArray;
	
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
?>