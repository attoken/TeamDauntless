<?php
require_once 'LeFamilyDB.php';;

class LeFamilyController
{
	public $LFDB;
	function __construct()
	{
		$this->LFDB = new LeFamilyDB();
	}
	function get_family_details()
	{
		$response = $this->LFDB->selectFamily();
		
		var_dump(json_encode($response));
		
	}
	/**
	 * retrieve userid with phone number
	 * helper function
	 * @param int $phoneNumber
	 * @return multitype:
	 */
	function retrieve_user_ID($phoneNumber)
	{
		$response = $this->LFDB->select_userID_from_userPhone($phoneNumber);
		return $response->message;
	}
	/**
	 * Check for existing user in loading page
	 * @param int $phoneNumber
	 * @return string
	 */
	function check_for_existing_user($phoneNumber)
	{
		$response = $this->LFDB->select_userID_from_userPhone($phoneNumber);
		return json_encode($response);
	}
	/**
	 * Create user
	 * @param array $user_details
	 */
	function create_user($userDetails)
	{
		$userName = $userDetails['name'];
		$userPhoneNumber = $userDetails['phoneNumber'];
		$userSurname = $userDetails['surname'];
		$response = $this->LFDB->insert_user($userName, $userPhoneNumber, $userSurname);
		return json_encode($response);
	}

	function retrieve_pet_info($phoneNumber)
	{
		$userID = $this->retrieve_user_ID($phoneNumber);
	}
	
	function create_family($familyDetails)
	{
		$response = $this->retrieve_user_ID($familyDetails['phoneNumber']);	
		$userID = $response[0];
		$familyName = $familyDetails['familyName'];
		$response = $this->LFDB->insert_family($familyName, $userID);
		if($response->success == true)
		{
			$response = $this->LFDB->select_familyID($familyName, $userID);
			$familyID = $response->message[0];
			$this->LFDB->insert_family_user($userID, $familyID, 'Admin');
			return $familyID;
		}
		else
		{
			return "There exist such a family";
		}
		
	}
	
	
}
//$array = array("name" => "hi", "phoneNumber" => "1234568", "surname" => "ber");
$array = array("familyName" => "berber", "phoneNumber" => "1234568", "surname" => "ber");
var_dump($array);
$test = new LeFamilyController();
$response = $test->create_family($array);
echo $response;
?>