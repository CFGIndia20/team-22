<?php
class Events {

private $conn;
private $table='events';
private $center_table='center_master';

//public $meeting_id;
public $type;
public $date;
public $manager_id;
public $center_id;


public function __construct($db) {
    $this->conn=$db;
}
public function create_event() {
    $query='INSERT INTO ' . $this->table . '
    SET
        type= :type,
        date= :date,
        manager_id= :manager_id,
        center_id= :center_id';


    $stmt=$this->conn->prepare($query);
    //$this->meeting_id=htmlspecialchars(strip_tags($this->meeting_id));
    $this->type=htmlspecialchars(strip_tags($this->type));
    $this->date=htmlspecialchars(strip_tags($this->date));
    $this->manager_id=htmlspecialchars(strip_tags($this->manager_id));
    $this->center_id=htmlspecialchars(strip_tags($this->center_id));
    
    //$stmt->bindParam(':meeting_id', $this->meeting_id);
    $stmt->bindParam(':type', $this->type);
    $stmt->bindParam(':date', $this->date);
    $stmt->bindParam(':manager_id', $this->manager_id);
    $stmt->bindParam(':center_id', $this->center_id);
        

    if($stmt->execute()) {
        return true;
    }

    //printf("error: %s.\n", $stmt->error);
    return false;

}

public function show_events() {
   
    $query='SELECT 
    type, 
    date,
    manager_id,
    center_id 
    FROM 
    ' . $this->table;

    $stmt=$this->conn->prepare($query);
    $stmt->execute();
    while($row=$stmt->fetch(PDO::FETCH_ASSOC)){
        $query2 = "SELECT centerName FROM $this->center_table where center_id = :center_id" ;
        $stat = $this->conn->prepare($query2);
        $stat->bindparam(":center_id" , $row["center_id"]);
        $stat->execute();
        $row['centerName'] = $stat->fetch(PDO::FETCH_ASSOC)['centerName'];
       
       $this->data[] = $row;
    }
    
    return $this->data;
   


}

}
?>