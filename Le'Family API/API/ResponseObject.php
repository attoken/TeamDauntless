<?php
class ResponseObject
{
	function __construct()
	{
		$response = array();
		$response["success"]= 0;
		$response["message"] ="";
		$response["error"] = "";
	}
}