<?php include("parts_checkSession.php");
include('RestRequest.php');
$path = 'java.open.agmip.org/AgMIPWS2/';
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Language" content="en-US" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="css/frame.css" />
<title>Read Soil Files</title>
<script src="js/function.js" type="text/javascript">
</script>
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
</style>
</head>

<body>
<div id="container">
	<?php include("parts_header.php"); ?>
	<?php include("functionForSoil.php"); ?>
	<?php
		$fileNum = 1;
		$files = array();
		
		for($i = 0; $i < $fileNum; $i++) {
			$line = "";
			$flg[0] = "";
			$flg[1] = "";
			$flg[2] = "";
			$lineNo = 0;
			$ret = createSoilArray();
			
			$file = fopen($_FILES["FilePath"]["tmp_name"],"r") or exit("Unable to open file!");
			
			while(!feof($file)) {
				$lineNo++;
				$line = fgets($file);
				$flg = judgeContentType($line, $flg); // explode,splitStrToArray
				//echo "[line".$lineNo."],[".$flg[0]."],[".$flg[1]."],[".$flg[2]."]<br>"; //debug
				$ret = getSpliterResult($flg, $line, $ret);
			}
			fclose($file);
			
			//$ret = checkExpId($ret, $_FILES["FilePath"]["name"]); // TODO if there must be a soil id, then add this method to do that
			
			$files[$i] = $ret;
			printSoilArray($ret); //debug
			//print_r($ret); //debug
			

			$c = 1;
			$profiles = array();
			
			foreach ($ret['site'] as $site){
				// Profile
				$profile = array();
				
				$profile['soilId'] = $site['pedon'];
				$profile['address'] = $ret['address'];
				$profile['site'] = $site['ssite'];
				$profile['soillat'] = $site['slat'];
				$profile['soillong'] = $site['slong'];
				$profile['salb'] = $site['salb'];
				$profile['slu1'] = $site['u'];
				$profile['sldr'] = $site['swcon'];
				$profile['slro'] = $site['cn2'];
				$profile['slnf'] = $site['slnf'];
				$profile['smhb'] = $site['smhb'];
				$profile['smpx'] = $site['smpx'];
				$profile['smke'] = $site['smke'];
				
				// Layers
				foreach ($site['layer1'] as $lay){
					$layer1PK = array();
					// Start temp
					$layer1PK['soilId'] = $site['pedon'];
					$layer1PK['soilFileId'] = 'aa';
					$layer1PK['sllb'] = ++$c;
					// End temp
				
					$layer1['soilProfileLayerPK'] = $layer1PK;
					
					$layer1['slmh'] = $lay['mh'];
					$layer1['slll'] = $lay['ll'];
					$layer1['sldul'] = $lay['dul'];
					$layer1['slsat'] = $lay['sat'];
					$layer1['slrgf'] = $lay['rgf'];
					$layer1['sbdm'] = $lay['db'];
					$layer1['sloc'] = $lay['oc'];
					$layer1['slcl'] = $lay['clay'];
					$layer1['slsi'] = $lay['silt'];
					$layer1['slcf'] = $lay['stones'];
					$layer1['slni'] = $lay['totn'];
					$layer1['slcec'] = $lay['cec'];
					$layer1['sadc'] = $lay['sadc'];
				
					$profile['soilProfileLayerCollection'][] = $layer1;
				}
				
				foreach ($site['layer2'] as $lay){
					$layer2PK = array();
					// Start temp
					$layer2PK['soildId'] = $site['pedon'];
					$layer2PK['soilFileId'] = 'bb';
					$layer2PK['sllb'] = ++$c;
					// End temp
					
					$layer2['soilProfilePK'] = $layer2PK;
					
					$layer2['slpx'] = $lay['extp'];
					$layer2['slpt'] = $lay['totp'];
					$layer2['slpo'] = $lay['orgp'];
					$layer2['slca'] = $lay['caco'];
					$layer2['slal'] = $lay['extal'];
					$layer2['slfe'] = $lay['extfe'];
					$layer2['slmn'] = $lay['extmn'];
					$layer2['slbs'] = $lay['totbas'];
					$layer2['slpa'] = $lay['pterma'];
					$layer2['slpb'] = $lay['ptermb'];
					$layer2['slke'] = $lay['exk'];
					$layer2['slmg'] = $lay['exmg'];
					$layer2['slna'] = $lay['exna'];
					$layer2['slsu'] = $lay['exts'];
					$layer2['slec'] = $lay['slec'];
					$layer2['slca'] = $lay['slca'];
					
					$profile['soilProfileLayerCollection'][] = $layer2;
				}
				
				$profiles[] = json_encode($profile);
			}
			
			$str_profiles = '['.implode(',', $profiles).']';

			$request = new RestRequest($path . 'resources/soilProfilesService', 'POST', $str_profiles);
			$request->setAcceptType('application/json');
			$request->execute();
	
			$response = $request->getResponseInfo();
	
			if ($response['http_code'] == "201"){
				echo '<strong>Soil profile created</strong><br/><br/>';
			}
			else{
				echo '<span style="color:#ff0000">Error - Soil Profile: '.$response['http_code'].'</span><br/>';
			}
		}

	?>
	<div id="content">
		<form id="form1" method="post" action="saveFiles.php">
			<table class="style2" style="width: 600px" align="center">
				<!--tr>
					<td class="style1">Experiment ID</td>
					<td class="style1">Treatment Num</td>
					<td class="style1">Treatment Name</td>
					<td class="style1" style="width: 81px">Select?</td>
				</tr-->
				<?php
					
					echo "<input name='files' type='hidden' value='" . json_encode($files) . "' />";
				?>
			</table>
			<br />
			<p class="style5">
				<span><input name="Button1" type="button" value="Back" onclick="goBack()"/></span>&nbsp;
				<span><input id="save" name="save" type="button" value="Save" onclick="checkChkbox('<?php echo $jsCheck; ?>')" /></span></form>
			</p>
		</form>
	</div>
	<?php include("parts_footer.php"); ?>
</div>
</body>
</html>
