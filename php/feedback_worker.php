<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: POST');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type,Access-Control-Allow-Methods, Authorization, X-Requested-With');

require_once 'Database.php';
require_once 'Worker.php';

$instance = Database::getInstance();
$db = $instance->getConnection();
$data = json_decode(file_get_contents("php://input"));

$worker_id = $data->worker_id;
$task_id = $data->task_id;
$typeName = $data->typeName;
$quantity = $data->quantity; //to be added in level 0
$date = $data->date;
$description = $data->description;

$worker = new Worker($db);
$data = $worker->takeFeedback($worker_id , $task_id , $typeName , $quantity, $date, $description ) ;

echo json_encode($data) ;


