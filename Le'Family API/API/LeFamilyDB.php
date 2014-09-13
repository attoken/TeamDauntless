<?php
require_once 'ResponseObject.php';
require_once 'DBLayer.php'; 



class LeFamilyDB 
{
	public $response;
	public $db;
	
	function __construct()
	{
		$this->response = new ResponseObject();
		$this->db = new DBLayer();
	}
	function insert_family($familyName, $userID)
	{
		//create your insert statement
		$insert_stmt = sprintf("INSERT INTO family 
								(familyName, familyAdminID) 
								VALUES ('%s', '%s')", 
								$familyName, $userID);	
		
		$results = $this->db->query($insert_stmt);
		
		
		if($results!=false)
		{
			$this->response->setResponse(true);
		}
		else
		{
			$this->response->setResponse(false,"","Failed to update any information to family");
		}
		return $this->response;
	}
	function delete_family()
	{
		//create your delete statement

	}
	function select_family()
	{		
		//create your select statement
		$select_stmt = 'SELECT * FROM family;';
		$results = $this->db->query($select_stmt);
		if($results!=false)
		{
			$this->response->setResponse(true, $results);
		}
		else
		{
			$this->response->setResponse(false,"","Failed to retreive any information from Family");
		}
		$this->db->disconnect();
		return $this->response;
	}
	function select_familyID($familyName, $adminID)
	{
		//create your select statement
		$select_stmt = sprintf("SELECT familyID FROM family WHERE familyName = '%s' AND familyAdminID = '%s';"
								, $familyName ,$adminID);
		
		$results = $this->db->query($select_stmt);
		$fetch = mysqli_fetch_row($results);
		
		if($results!=false)
		{
			$this->response->setResponse(true, $fetch);
		}
		else
		{
			$this->response->setResponse(false,"","Failed to retreive family ID from Family");
		}
		
		return $this->response;
	}
	
	function insert_family_user($familyID, $userID, $position)
	{
		//create your insert statement
		$insert_stmt = sprintf("INSERT INTO family_user
				(family_userUserID, family_userFamilyID, family_userPosition)
				VALUES ('%s', '%s', '%s')",
				$familyID, $userID, $position);
		var_dump($insert_stmt);
		$results = $this->db->query($insert_stmt);
		
		
		if($results!=false)
		{
			$this->response->setResponse(true);
		}
		else
		{
			$this->response->setResponse(false,"","Failed to update any information to family_user");
		}
		return $this->response;
	}
	
	function insert_user($userName, $userPhoneNumber, $userSurname)
	{
		//create your insert statement
		$insert_stmt = sprintf("INSERT INTO users 
								(usersName, usersPhoneNumber, usersSurname) 
								VALUES ('%s', '%s', '%s')", 
								$userName, $userPhoneNumber, $userSurname);
		
		$results = $this->db->query($insert_stmt);
		
		if($results!=false)
		{
			$this->response->setResponse(true);
		}
		else
		{
			$this->response->setResponse(false,"","Failed to update any information to users");
		}
		return $this->response;
	}
	function delete_user()
	{
	
	}
	function select_user()
	{
	
	}
	function select_userID_from_userPhone($phoneNumber)
	{
		$select_stmt = sprintf('SELECT usersID FROM users WHERE usersPhoneNumber = %s;', $phoneNumber);
		$results = $this->db->query($select_stmt);
		$fetch = mysqli_fetch_row($results);
		if($results!=false)
		{
			$this->response->setResponse(true, $fetch);
		}
		else
		{
			$this->response->setResponse(false,"","Failed to retreive any information from users");
		}
		return $this->response;
	}
	
	function insert_event()
	{
	
	}
	function delete_event()
	{
	
	}
	function select_event()
	{
	
	}
	
	function insert_voice_message()
	{
	
	}
	function delete_voice_message()
	{
	
	}
	function select_voice_message()
	{
	
	}
	
	function insert_photo()
	{
	
	}
	function delete_photo()
	{
	
	}
	function select_photo()
	{
	
	}
	
	function select_panic_status()
	{
		
	}
	
}


?>