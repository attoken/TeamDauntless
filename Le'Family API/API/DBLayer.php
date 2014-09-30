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
		$this->db_user = "root";
		$this->db_pass = "";
		$this->db_host = "localhost";
		$this->db_name = "lefamily";
		$this->connect();
	}
	
	function connect()
	{
		$this->db_link = mysqli_connect($this->db_host, $this->db_user, $this->db_pass)
		or die("Could not make connection to MySQL");
		mysqli_select_db($this->db_link, $this->db_name)
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
		var_dump($q_statement);
		$result = mysqli_query($this->db_link, $q_statement );
		$returnArray = array();
		$i = 0;
		
		//return successful none select queries
		if($result)
		{
			return $result;
		}
		//return unsuccessful query
		else if(!$result)
		{
			return $result;
		}
		//return select query
		else
		{
			while($row = mysqli_fetch_array($result,MYSQLI_BOTH))
			{
				if ($row)
					$returnArray[$i++]=$row;
			}
			mysqli_free_result($result);
			return $returnArray;
		}
		
	}
	
	function execute($exe_statement, $params)
	{
		
		
		if(!isset($this->db_link))
			$this->connect();
		$insert_stmt = mysqli_prepare($this->db_link, $exe_statement);
		
		mysqli_stmt_bind_param($insert_stmt, "sss",$var1, $var2, $var3);
		$val1 = 'Bordeaux';
		$val2 = 'FRA';
		$val3 = 'Aquitaine';
		mysqli_stmt_execute($insert_stmt);
		
		if(!$result)
			error_log("Database not updated");
		return $result;
	}
}

?>
