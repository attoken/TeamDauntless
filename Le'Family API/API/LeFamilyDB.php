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
	function delete_family($familyID)
	{

		$delete_stmt = sprintf("DELETE FROM family
				WHERE familyID = %s",
				$familyID);

		$results = $this->db->query($delete_stmt);
		
		if($results!=false)
		{
			$this->response->setResponse(true);
		}
		else
		{
			$this->response->setResponse(false,"","Failed to delete from family");
		}
		return $this->response;
	}
	function select_family($familyID)
	{		
		//create your select statement
		$select_stmt = sprintf('SELECT * FROM family WHERE familyID = %s;', $familyID);
		$results = $this->db->query($select_stmt);
		$fetch = mysqli_fetch_all($results, MYSQLI_ASSOC);
		if($results!=false)
		{
			$this->response->setResponse(true, $fetch);
		}
		else
		{
			$this->response->setResponse(false,"","Failed to retreive any information from Family");
		}
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
			$this->response->setResponse(false,"","Failed to retrieve family ID from Family");
		}
		
		return $this->response;
	}
	
	function insert_family_user($familyID, $userID, $position, $name, $admin )
	{
		//create your insert statement
		$insert_stmt = sprintf("INSERT INTO family_user
				(family_userUserID, family_userFamilyID, family_userPosition, family_userName, family_userAdminFlag)
				VALUES ('%s', '%s', '%s', '%s', %s)",
				$familyID, $userID, $position, $name, $admin);
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
	function select_family_member_by_family_id($familyID)
	{
		$select_stmt = sprintf("SELECT family_userUserID FROM family_user WHERE family_userFamilyID = %s;"
				, $familyID);
		
		$results = $this->db->query($select_stmt);
		
		
		$fetch = mysqli_fetch_all($results, MYSQLI_NUM);
		
		if($results!=false)
		{
			$this->response->setResponse(true, $fetch);
		}
		else
		{
			$this->response->setResponse(false,"","Failed to retreive family member from family_user");
		}
		
		return $this->response;
	}
	function select_family_id_by_user_id($userID)
	{
		$select_stmt = sprintf("SELECT family_userFamilyID FROM family_user WHERE family_userUserID = %s;"
				, $userID);
	
		$results = $this->db->query($select_stmt);
	
		$fetch = mysqli_fetch_all($results, MYSQLI_ASSOC);

		if($results!=false)
		{
			$this->response->setResponse(true, $fetch);
		}
		else
		{
			$this->response->setResponse(false,"","Failed to retreive family member from family_user");
		}
	
		return $this->response;
	}
	function delete_family_member($userID, $familyID)
	{
		//create your insert statement
		if($familyID != -1)
		{
			$delete_stmt = sprintf("DELETE FROM family_user
				WHERE family_userUserID = %s AND family_userFamilyID = %s",
				$userID, $familyID);
		}
		else
		{
			$delete_stmt = sprintf("DELETE FROM family_user
					WHERE family_userUserID = %s",
					$userID);
		}
		
		$results = $this->db->query($delete_stmt);
		
		if($results!=false)
		{
			$this->response->setResponse(true);
		}
		else
		{
			$this->response->setResponse(false,"","Failed to delete from family_user");
		}
		return $this->response;
	}
	function insert_user( $userPhoneNumber)
	{
		//create your insert statement
		$insert_stmt = sprintf("INSERT INTO users 
								(usersPhoneNumber) 
								VALUES ('%s')", 
								$userPhoneNumber);
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
	function delete_user($userID)
	{
		//create your insert statement
		$delete_stmt = sprintf("DELETE FROM users
				WHERE usersID = %s ",
				$userID);
		
		$results = $this->db->query($delete_stmt);
		
		if($results!=false)
		{
			$this->response->setResponse(true);
		}
		else
		{
			$this->response->setResponse(false,"","Failed to delete from users");
		}
		return $this->response;
	}
	function select_user($userID, $familyID)
	{
		$select_stmt = sprintf("SELECT u.*, fu.family_userName, fu.family_userAdminFlag FROM users AS u 
								INNER JOIN family_user AS fu ON u.usersID = fu.family_userUserID
								WHERE u.usersID = %s AND fu.family_userFamilyID = %s;"
				, $userID, $familyID);
		error_log("selectuser".$select_stmt);
		$results = $this->db->query($select_stmt);		
		$fetch = mysqli_fetch_all($results, MYSQLI_ASSOC);
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
	function select_userID_from_userPhone($phoneNumber)
	{
		$select_stmt = sprintf('SELECT usersID FROM users WHERE usersPhoneNumber = %s;', $phoneNumber);
		$results = $this->db->query($select_stmt);

		$fetch = mysqli_fetch_row($results);

		if($fetch!=null)
		{
			$this->response->setResponse(true, $fetch);
		}
		else
		{
			$this->response->setResponse(false);
		}

		return $this->response;
	}
	function select_family_created_from_user_admin_id($adminID)
	{
		//create your select statement
		$select_stmt = sprintf("SELECT familyID FROM family WHERE familyAdminID = '%s';"
				,$adminID);
		
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
	/**
	 * Inserts event and retrieve event index for insertion to event_user table
	 * @param String $eventType
	 * @param String $eventTitle
	 * @param String $userID
	 * @param String $eventDateTime
	 */
	function insert_event($eventType,$eventTitle, $userID,$eventDateTime)
	{
		//create your insert statement
		$insert_stmt = sprintf("INSERT INTO event
				( eventType, eventTitle, eventHostID, eventDateTime) 
				VALUES ( '%s', '%s', '%s', '%s')",
				$eventType,$eventTitle, $userID,$eventDateTime);
		
		$results = $this->db->query($insert_stmt);
		$eventID = $this->db->get_id();
		
		if($results!=false)
		{
			$this->response->setResponse(true, $eventID);
		}
		else
		{
			$this->response->setResponse(false,"","Failed to update any information to event");
		}
		return $this->response;
	}
	function delete_event($eventID)
	{
		
		$delete_stmt = sprintf("DELETE FROM event
				WHERE eventID = %s",
				$eventID);
		
		$results = $this->db->query($delete_stmt);
		
		if($results!=false)
		{
			$this->response->setResponse(true);
		}
		else
		{
			$this->response->setResponse(false,"","Failed to delete from event");
		}
		return $this->response;
	}
	function select_event($familyID)
	{
		$select_stmt = sprintf("SELECT * FROM event WHERE eventHostID = (SELECT family_userUserID from family_user WHERE family_userFamilyID = %s);"
				, $familyID);
		error_log($select_stmt);
		$results = $this->db->query($select_stmt);		
		$fetch = mysqli_fetch_all($results, MYSQLI_ASSOC);
		if($results!=false)
		{
			$this->response->setResponse(true, $fetch);
		}
		else
		{
			$this->response->setResponse(false,"","Failed to retreive family ID from event");
		}
		
		return $this->response;
	}
	function update_event($eventID, $eventType, $eventTitle, $eventDateTime)
	{
		$update_stmt = sprintf("UPDATE event
				SET eventType = '%s', eventTitle  = '%s', eventDateTime  = '%s'
				WHERE eventID = %s",
				$eventType, $eventTitle, $eventDateTime, $eventID);
		error_log($update_stmt);
		$results = $this->db->query($update_stmt);
		
		if($results!=false)
		{
			$this->response->setResponse(true);
		}
		else
		{
			$this->response->setResponse(false,"","Failed to update from event");
		}
		return $this->response;
	}
	
	function insert_user_event($userID,$eventID)
	{
		//create your insert statement
		$insert_stmt = sprintf("INSERT INTO user_event
				( user_eventUserID, user_eventEventID)
				VALUES ( '%s', '%s')",
				$userID,$eventID);
		error_log($insert_stmt);
		$results = $this->db->query($insert_stmt);
		
		if($results!=false)
		{
			$this->response->setResponse(true);
		}
		else
		{
			$this->response->setResponse(false,"","Failed to insert any information to user_event");
		}
		return $this->response;
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