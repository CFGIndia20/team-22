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

$worker_id = $data->worker_id ;
$flag = $data->flag ;
$manager_id = $data->manager_id;
$w =  new Worker($db);
$result = $w->giveFeedback($worker_id,$flag,$manager_id);
echo json_encode($result);

