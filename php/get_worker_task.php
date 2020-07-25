<?php

header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: GET');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type,Access-Control-Allow-Methods, Authorization, X-Requested-With');

require_once 'Database.php';
require_once 'Task_Worker.php';

$instance = Database::getInstance();
$db = $instance->getConnection();
$tasks = new Task_Worker($db);

$worker_id = $_GET["worker_id"] ;

$data = $tasks->get_worker_task($worker_id);
echo json_encode($data);




