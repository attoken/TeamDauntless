<?php

class DBLayer
{
	var $db_name;
	var $db_user;
	var $db_pass;
	var $db_host;
	var $db_link;
	
	function __construct()
	{
		$this->db_user = "bernard";
		$this->db_pass = "lefamily";
		$this->db_host = "localhost";
		$this->db_name = "le'family";
		$this->connect();
	}
	
	function connect()
	{
		$this->db_link = mysqli_connect($this->db_host, $this->database_user, $this->database_pass)
		or die("Could not make connection to MySQL");
		mysqli_select_db($this->db_name)
		or die ("Could not open database: ". $this->database_name);
	}
	
	function disconnect()
	{
		if(isset($this->db_link))
			mysqli_close($this->db_link);
		else
			mysqli_close();
	}
	
	function query($q_statement)
	{
		if(!isset($this->db_link))
			$this->connect();
		$result = mysqli_query($q_statement, $this->db_link);
		$returnArray = array();
		$i = 0;
		while($row = mysqli_fetch_array($result,MYSQLI_BOTH))
		{
			if ($row)
				$returnArray[$i++]=$row;
		}
		mysqli_free_result($result);
		return $returnArray;
	}
	
	function execute($exe_statement)
	{
		if(!isset($this->db_link))
			$this->connect();
		$result = mysqli_execute($exe_statement);
		if(!$result)
			error_log("Database not updated");
	}
}
?>
