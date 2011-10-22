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

function writeSoilFile($profile){
	global $path;
	$str = '*SOILS:'.$profile->address."\r\n\r\n";
				
	$str .= '*'.$profile->soilId.'  5102201    SIC      218   Tavares Coated Variant Sand'."\r\n";
				
	$str .= '@SITE        COUNTRY          LAT     LONG SCS FAMILY'."\r\n";
	$site = str_pad($profile->site, 12);
	$country = str_pad('USA', 12);
	$lat = formatStr("  %5.3f", $profile->soillat);
	$long = formatStr("  %5.3f", $profile->soillong);
	$scs = ' Generic';
	$str .= ' '.$site . $country . $lat . $long . $scs . "\r\n";

	$str .= '@ SCOM  SALB  SLU1  SLDR  SLRO  SLNF  SLPF  SMHB  SMPX  SMKE'."\r\n";

	$scom = str_pad('-99', 6, " ", 0);
	$salb = str_pad($profile->salb, 6, " ", 0);
	$slu1 = str_pad($profile->slu1, 6, " ", 0);
	$sldr = str_pad($profile->sldr, 6, " ", 0);
	$slro = str_pad($profile->slro, 6, " ", 0);
	$slnf = str_pad($profile->slnf ,6, " ", 0);
	$slpf = str_pad('-99', 6, " ", 0);
	$smhb = str_pad($profile->smhb, 6, " ", 0);
	$smpx = str_pad($profile->smpx, 6, " ", 0);
	$smke = str_pad($profile->smke, 6, " ", 0);

	$str .= $scom . $salb . $slu1 . $sldr . $slro . $slnf . $slpf . $smhb . $smpx . $smke . "\r\n";
	$str .= '@  SLB  SLMH  SLLL  SDUL  SSAT  SRGF  SSKS  SBDM  SLOC  SLCL  SLSI  SLCF  SLNI  SLHW  SLHB  SCEC  SADC'."\r\n";
	
	foreach ($profile->soilProfileLayerCollection->soilProfileLayer as $layer){
		$slb = str_pad('8', 6, " ", 0);
		$slmh = str_pad($layer->slmh, 6, " ", 0);
		$slll = str_pad(formatStr(" %4.3f", $layer->slll), 6, " ", 0);
		$sdul = str_pad(formatStr(" %4.3f", $layer->sldul), 6, " ", 0);
		$ssat = str_pad($layer->slsat, 6, " ", 0);
		$srgf = str_pad('-99', 6, " ", 0);
		$ssks = str_pad(formatStr(" %3.1f", '20.8'), 6, " ", 0);
		$sbdm = str_pad(formatStr(" %3.2f", '1.03'), 6, " ", 0);
		$sloc = str_pad(formatStr(" %3.2f", $layer->sloc), 6, " ", 0);
		$slcl = str_pad(formatStr(" %3.1f", $layer->slcl), 6, " ", 0);
		$slsi = str_pad(formatStr(" %3.1f", $layer->slsi), 6, " ", 0);
		$slcf = str_pad($layer->slcf, 6, " ", 0);
		$slni = str_pad($layer->slni, 6, " ", 0);
		$slhw = str_pad('7.4', 6, " ", 0);
		$slhb = str_pad('-99', 6, " ", 0);
		$scec = str_pad(formatStr(" %3.1f", "61.7"), 6, " ", 0);
		$sadc = str_pad('-99', 6, " ", 0);
	
		$str .= $slb . $slmh . $slll . $sdul . $ssat . $srgf . $ssks . $sbdm . $sloc . $slcl . $slsi . $slcf . $slni . $slhw . $slhb . $scec . $sadc . "\r\n";
	}

	echo $str;
}

if (isset($_POST['sid'])){
	global $path;
	$sid = $_POST['sid'];
	if (!is_numeric($sid)){
		die('The wid field must be numeric');
	}
	
	$request = new RestRequest($path . 'resources/soilProfiles/'.$sid.'/?expandLevel=2', 'GET');
	$request->setAcceptType('application/json');
	$request->execute();
			
	$profile = json_decode($request->getResponseBody());
	
	header('Content-type:application/force-download');
	header('Content-Transfer-Encoding:Binary');
	header('Content-Disposition:attachment;filename=test.soil');
	
	echo writeSoilFile($profile);
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
				<td class="style1">Soil ID</td>
				<td class="style1">Download</td>
			</tr>
			<?php
			$request = new RestRequest($path . 'resources/soilProfiles', 'GET');
			$request->setAcceptType('application/json');
			$request->execute();
			
			$profiles = json_decode($request->getResponseBody());

			foreach ($profiles->soilProfile as $profile){
				echo '<form method="post"><input type="hidden" name="sid" value="'.$profile->sid.'" />';
				echo '<tr><td>'.$profile->sid.'</td><td>'.$profile->soilId.'</td><td><input type="submit" value="Download" /></td></tr></form>';
			}
			?>
		</table>
		<br />
	</div>
	<?php include("parts_footer.php"); ?>
</div>
</body>

</html>
