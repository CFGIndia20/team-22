<?php

header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');
include_once 'Database.php';
include_once 'Events.php';

$instance = Database::getInstance();
$db = $instance->getConnection();

$events=new Events($db);
$data=$events->show_events();
echo json_encode($data);


?>