<?php

 // Singleton to connect db.
 class Database {
    // Hold the class instance.
    private static $instance = null;
    private $conn;

//    private $host = 'us-cdbr-east-02.cleardb.com';
//    private $user = 'bee87445c7e2b3';
//    private $pass = '';
//    private $name = 'heroku_9867e6b5b9d2385';
    private $host = 'localhost' ;
    private $user = 'root' ;
    private $pass = '';
    private $name = 'umeed';

    // The db connection is established in the private constructor.
    private function __construct()
    {
      $this->conn = new PDO("mysql:host={$this->host};
      dbname={$this->name}", $this->user,$this->pass,
      array(PDO::MYSQL_ATTR_INIT_COMMAND => "SET NAMES 'utf8'"));
    }

    public static function getInstance()
    {
      if(!self::$instance)
      {
        self::$instance = new Database();
      }

      return self::$instance;
    }

    public function getConnection()
    {
      return $this->conn;
    }
 }

?>