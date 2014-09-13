<?php
 class ResponseObject
{
	var $success;
	var $message = array();
	var $error;
	function __construct()
	{
		$success = 0;
		$message ;
		$error = "";
	}
	function setResponse($success, $message = "", $error = "")
	{
		if($success)
		{
			$this->success = $success;
			$this->message = $message;
		}
		else
		{
			$this->success = $success;
			$this->error = $error;
		}
	}
}

?>