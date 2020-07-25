<?php
    class Login
    {

        // DATABASE CONNECTION AND TABLE NAME
        private $conn;
        private $workerTable = "heroku_9867e6b5b9d2385.worker";

        private $worker_id;
        private $is_manager;
        private $name ;
        // DATABASE CONNECTION
        public function __construct($db)
        {
            $this->conn = $db;
        }

        // READ USER
        public function checkLogin($contact,$password)
        {
            $query = "SELECT worker_id, is_manager, name FROM $this->workerTable WHERE contact=:contact AND password=:password";
            $findUser = $this->conn->prepare($query);
            $findUser->bindParam(":contact", $contact);
            $findUser->bindParam(":password", $password);
            $findUser->execute();
            $temp = $findUser->fetch(PDO::FETCH_ASSOC);

            //CHECKING VALID OR INVALID
            if($temp == NULL)
            {
                return false;
            }
            else
            {

                return $temp;
            }

        }
    }
