<?php
require_once 'ResponseObject.php';
require_once 'DBLayer.php'; 

$response = new ResponseObject();

class LeFamilyDB 
{
	
	function insertFamily()
	{

		//create your insert statement
		$insert_stmt = '';	
		$db->execute($insert_stmt);
		$db->disconnect();
	}
	function deleteFamily()
	{
		//create your delete statement
		$delete_stmt = '';
		$db->execute($delete_stmt);
		$db->disconnect();
	}
	function selectFamily()
	{
		$db = new DBLayer();
		//create your select statement
		$select_stmt = 'SELECT * FROM family;';
		$results = $db->query($select_stmt);
		if($results!=false)
		{
			$response["success"] = true;
			$response["message"] = $results;
		}
		else
		{
			$response["success"] = false;
			$response["error"] = "Failed to retreive any information from Family";
		}
		var_dump($response);
		$db->disconnect();
		return $response;
	}
	
	function insertUser()
	{
	
	}
	function deleteUser()
	{
	
	}
	function selectUser()
	{
	
	}
	
	function insertPet()
	{
	
	}
	function deletePet()
	{
	
	}
	function selectPet()
	{
	
	}
	
	function insertEvent()
	{
	
	}
	function deleteEvent()
	{
	
	}
	function selectEvent()
	{
	
	}
	
	function insertVoiceMessage()
	{
	
	}
	function deleteVoiceMessage()
	{
	
	}
	function selectVoiceMessage()
	{
	
	}
	
	function insertPhoto()
	{
	
	}
	function deletePhoto()
	{
	
	}
	function selectPhoto()
	{
	
	}
	
	function selectPanicStatus()
	{
		
	}
	
}

$test = new LeFamilyDB();
$test->selectFamily();
?>