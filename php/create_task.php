<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: POST');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type,Access-Control-Allow-Methods, Authorization, X-Requested-With');
require_once 'Database.php';
require_once 'Task.php';

$instance = Database::getInstance();
$db = $instance->getConnection();
$task=new Task($db);

$data=json_decode(file_get_contents("php://input"));

$task->quantity=$data->quantity;
$task->product_id=$data->product_id;
$task->end_date=$data->end_date;

if($task->create_task()) {
    echo json_encode(array(
        'quantity' => $task->quantity,
        'product_id' => $task->product_id,
        'end_date' => $task->end_date
    )
);
}
else {
    echo json_encode(
        array('message' => 'task not created')
    );
}
?>