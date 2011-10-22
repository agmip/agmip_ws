<?php include("parts_checkSession.php"); 
include('RestRequest.php');
$path = 'java.open.agmip.org/AgMIPWS2/';

function formatStr($format, $str) {
	// check if is not unuse value; if so, change to text format output
	if ($str != -99 && $str != "-99") {
		return sprintf($format, $str);
	} else {		
		// if float
		if (stripos($format, ".") >= 2) {
			$format = substr($format, 0, strlen($format) - 3) . "s"; // TODO: if furture use more than 10 bit decimal, there should be revised
		} else {
			$format = substr($format, 0, strlen($format) - 1) . "s";
		}
		return sprintf($format, $str);
	}
}

function writeWthFile($weather){
	global $path;
	$str = '*WEATHER DATA : '.$weather->site.', '.$weather->fdName."\r\n\r\n";
	$str .= '@ INSI      LAT     LONG  ELEV   TAV   AMP REFHT WNDHT'."\r\n";
			
	$lat =  str_pad(formatStr(" %5.3f", $weather->lat), 9, " ", 0);
	$long = str_pad(formatStr(" %5.3f", $weather->long1), 9, " ", 0);
	$elev = str_pad(formatStr(" %3d", $weather->elev), 6, " ", 0);
	$tav = str_pad(formatStr(" %3.1f", $weather->tav), 6, " ", 0);
	$tamp = str_pad(formatStr(" %3.1f", $weather->tamp), 6, " ", 0);
	$refht = formatStr(" %5.2f", $weather->refht);
	$wndht = formatStr(" %5.2f", $weather->wndht);
			
	$str .= '  '.$weather->wstaId. $lat . $long . $elev . $tav . $tamp . $refht . $wndht ."\r\n";
			
	$request = new RestRequest($path . 'resources/weatherDailies/', 'GET');
	$request->setAcceptType('application/json');
	$request->execute();

	$files = json_decode($request->getResponseBody());
		
	$str .= '@DATE  SRAD  TMAX  TMIN  RAIN';
			
	foreach ($files->weatherDaily as $daily){
		$date = $daily->weatherDailyPK->wtyr;
		$srad = str_pad(formatStr(" %3.1f", $daily->srad), 6, " ", 0);
		$tmax = str_pad(formatStr(" %3.1f", $daily->tmax), 6, " ", 0);
		$tmin = str_pad(formatStr(" %3.1f", $daily->tmin), 6, " ", 0);
		$rain = str_pad(formatStr(" %3.1f", $daily->rain), 6, " ", 0);
				
		$str .= "\r\n". $date . $srad . $tmax . $tmin . $rain;
	}
	
	echo $str;
}

if (isset($_POST['wid'])){
	global $path;
	$wid = $_POST['wid'];
	if (!is_numeric($wid)){
		die('The wid field must be numeric');
	}
	
	$request = new RestRequest($path . 'resources/weatherSources/'.$wid, 'GET');
	$request->setAcceptType('application/json');
	$request->execute();
	
	$source = json_decode($request->getResponseBody());
	
	header('Content-type:application/force-download');
	header('Content-Transfer-Encoding:Binary');
	header('Content-Disposition:attachment;filename=test.wth');
	
	echo writeWthFile($source);
	die();
}
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Language" content="en-US" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="css/frame.css" />
<title>List</title>
<script src="js/function.js" type="text/javascript"></script>
<style type="text/css">
.style1 {
	border: 1px solid #000000;
	background-color: #C0C0C0;
}
.style2 {
	border: 2px solid #000000;
}
.style3 {
	border: 1px solid #000000;
}
.style4 {
	border: 1px solid #000000;
	text-align: center;
}
.style5 {
				text-align: center;
}
.style7 {
				border: 1px solid #000000;
				text-align: center;
				font-size: x-small;
}
</style>
</head>

<body>
<div id="container">
	<?php include("parts_header.php"); ?>
	<div id="content">
		<p class="style5">
			<span><input id="addNewFile" name="addNewFile" type="button" value="Add New File" onclick="goto('inputWthFiles.php')"/></span>
			<span><input id="backToMenu" name="backToMenu" type="button" value="Back To Menu" onclick="goto('menu.php')"/></span>
			<span><input id="GoToMap" name="GoToMap" type="button" value="Switch To Map" onclick="goto('listByMap.php')"/></span>
		</p>
		<table class="style2" style="width: 600px" align="center">
			<tr>
				<td class="style1">Unique ID</td>
				<td class="style1">WSTA</td>
				<td class="style1">Download</td>
			</tr>
			<?php
			$request = new RestRequest($path . 'resources/weatherSources', 'GET');
			$request->setAcceptType('application/json');
			$request->execute();
			
			$sources = json_decode($request->getResponseBody());

			foreach ($sources->weatherSource as $weather){
				echo '<form method="post"><input type="hidden" name="wid" value="'.$weather->wid.'" />';
				echo '<tr><td>'.$weather->wid.'</td><td>'.$weather->wstaId.'</td><td><input type="submit" value="Download" /></td></tr></form>';
			}
			?>
		</table>
		<br />
	</div>
	<?php include("parts_footer.php"); ?>
</div>
</body>

</html>
