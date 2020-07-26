<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: POST');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type,Access-Control-Allow-Methods, Authorization, X-Requested-With');
require_once 'Database.php';
require_once 'Events.php';

$instance = Database::getInstance();
$db = $instance->getConnection();
$events=new Events($db);

$data=json_decode(file_get_contents("php://input"));

$events->type=$data->type;
$events->date=$data->date;
$events->manager_id=$data->manager_id;
$events->center_id=$data->center_id;

if($events->create_event()) {
    echo json_encode(array(
        'type' => $events->type,
        'date' => $events->date,
        'manager_id' => $events->manager_id,
        'center_id' => $events->center_id
    )
);
}
else {
    echo json_encode(
        array('message' => 'event not created')
    );
}
?>