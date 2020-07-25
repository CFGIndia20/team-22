<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');
require_once 'Database.php';
require_once 'Worker.php';

$instance = ConnectDb::getInstance();
$db = $instance->getConnection();

$worker=new Worker($db);

// $result= $worker->worker_assigned_to_manager();


$worker->manager_id = isset($_GET['manager_id']) ? $_GET['manager_id'] : die();
$worker->worker_assigned_to_manager();
// echo json_encode($worker);
// $worker_arr=array(
//     'name' => $worker->name
// );

echo json_encode($worker->data);

?>
