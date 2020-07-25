<?php

header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: GET');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type,Access-Control-Allow-Methods, Authorization, X-Requested-With');

require_once 'Database.php';
require_once 'ManagerTasks.php';
$instance = Database::getInstance();
$db = $instance->getConnection();
$tasks = new ManagerTasks($db);

$worker_id = $_GET["worker_id"] ;

