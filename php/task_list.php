<?php

header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');
include_once 'Database.php';
include_once 'Task.php';

$instance = Database::getInstance();
$db = $instance->getConnection();

$tasks=new Task($db);
$data=$tasks->task_list();
echo json_encode($data);


?>