<?php
use Database\DBLayer;

class LeFamilyDB
{
	function __construct()
	{
		$db = new DBLayer();
	}
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
		//create your select statement
		$select_stmt = '';
		$db->query($select_stmt);
		$db->disconnect();
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

?>