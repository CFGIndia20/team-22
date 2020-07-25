<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');
require_once 'Database.php';
require_once 'Worker.php';

$instance = Database::getInstance();
$db = $instance->getConnection();

$worker=new Worker($db);

// $result= $worker->worker_assigned_to_manager();

/*
$num=$result->rowCount();

if($num>0) {
    $worker_arr=array();
    $worker_arr['data']=array();


    while($row=$result->fetch(PDO::FETCH_ASSOC)){
        extract($row);
        $worker_item=array(
            'name' => $name
        );
        array_push($worker_arr['data'], $worker_item);
    }
    echo json_encode($worker_arr);
}
else {
    echo json_encode(
        array('message' => 'no worker found')
    );
} 

*/

$worker->manager_id = isset($_GET['manager_id']) ? $_GET['manager_id'] : die();
$worker->worker_assigned_to_manager();
// echo json_encode($worker);
// $worker_arr=array(
//     'name' => $worker->name
// );

echo json_encode($worker->data);

?>
