<?php

require_once 'mysql_login.php';

class Database
{
private static $db = null;

private static $pdo;

final private function __construct()
{
try {
    self::getDB();
    }catch (PDOException $e) {
        $e.printStackTrace();
        }
        return self::$db;
}

public function getDB()
{
if (self::$pdo == null) {
    self::$pdo = new PDO(
        'mysql:dbname=' . DATABASE .
        ';host=' . HOSTNAME .
        ';port:63343;',
        USERNAME,
        PASSWORD,
        array(PDO::MYSQL_ATTR_INIT_COMMAND => "SET NAMES utf8")
        );
       self::$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        }
    return self::$pdo;
}

final protected funciont __clone()
{
}
function _destructor()
{
self::$pdo = null;
}
}
?>
