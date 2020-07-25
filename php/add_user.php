<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: POST');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type,Access-Control-Allow-Methods, Authorization, X-Requested-With');
include_once 'Database.php';
include_once 'Worker.php';

$instance = ConnectDb::getInstance();
$db = $instance->getConnection();

$worker=new Worker($db);

$data=json_decode(file_get_contents("php://input"));
$worker->name=$data->name;
$worker->password=$data->password;
$worker->contact=$data->contact;
$worker->center_id=$data->center_id;
$worker->manager_id=$data->manager_id;

if($worker->add_user()) {
    echo json_encode(
        array('message' => 'worker created')
    );
}
else {
    echo json_encode(
        array('message' => 'worker not created')
    );
}
?>
