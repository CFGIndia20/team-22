<?php
class Worker {

private $conn;
private $table='worker';

public $name;
public $password;
public $contact;
public $center_id;
public $manager_id;

public function __construct($db) {
    $this->conn=$db;
}

public function add_user() {
    $query ='INSERT INTO '. $this->table . '
    SET
       name = :name,
       password = :password,
       contact = :contact,
       center_id = :center_id,
       manager_id = :manager_id';

    $stmt=$this->conn->prepare($query);
    $this->name=htmlspecialchars(strip_tags($this->name));
    $this->password=htmlspecialchars(strip_tags($this->password));
    $this->contact=htmlspecialchars(strip_tags($this->contact));
    $this->center_id=htmlspecialchars(strip_tags($this->center_id));
    $this->manager_id=htmlspecialchars(strip_tags($this->manager_id));

    $stmt->bindParam(':name', $this->name);
    $stmt->bindParam(':password', $this->password);
    $stmt->bindParam(':contact', $this->contact);
    $stmt->bindParam(':center_id', $this->center_id);
    $stmt->bindParam(':manager_id', $this->manager_id);

    if($stmt->execute()) {
        return true;
    }

    //printf("error: %s.\n", $stmt->error);
    return false;

}


public function worker_assigned_to_manager() {
    $query='SELECT 
    name,
    worker_id 
    FROM 
    ' . $this->table . ' 
    WHERE 
    manager_id = ?';
    $stmt=$this->conn->prepare($query);
    $stmt->bindParam(1, $this->manager_id);
    $stmt->execute();
    $num=$stmt->rowCount();

if($num>0) {
    while($row = $stmt->fetch(PDO::FETCH_ASSOC))
        {
            // $this->name[]=$row['name'];
            // $this->worker_id[]=$row['worker_id'];
            $this->data[] = $row;
        }
    }
    /*
    $row=$stmt->fetch(PDO::FETCH_ASSOC);
    $this->name=$row['name'];
*/
}

}



?>
