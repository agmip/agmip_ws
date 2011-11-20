<?php
include('RestRequest.php');
$path = 'http://java.open.agmip.org/AgMIPWS2/';

if (isset($_POST['send'])){
	$experiment = file_get_contents('experiment-sample.xml');
	
	$request = new RestRequest($path . 'resources/experiments', 'POST', $experiment);
	$request->setAcceptType('application/xml');
	$request->execute();
	
	$response = $request->getResponseInfo();
	
	echo '<pre>';
	print_r($response);
	echo '</pre>';
	
	if ($response['http_code'] == '201'){
		echo '<p style="color:#0f9b00">Experiment created sucessfully.</p>';
		
		$experiment = simplexml_load_string($request->getResponseBody());
		
		echo '<p><strong>Experiment ID: '.$experiment->expId.'</strong></p>';
		
	}
	else{
		echo '<p style="color:#ff0000">Error. The experiment was not created</p>';
	}
}
?>

<html>
<head>
	<title>XML Example</title>
</head>

<body>

	<h1>Experiment XML File</h1>
	
	<p>This example reads a XML file in the same folder of this page and send</br>it to the AgMIP API.<br/>
	You can do this and view the debug info by clicking on the button below.
	</p>

	<form method="post">
		<input type="submit" name="send" value="Read and send the XML file" />
	</form>

</body>
</html>
