<?php
$mt = 0;
if (isset($_POST['action'])) {
    $action = $_POST['action'];
    if ($action == "OK") {
        $mt = $_POST['montant'];
        $client = new SoapClient("http://localhost:8085/BanqueWS?wsdl");
        $param = new stdClass();
        $param->montant = $mt;
        $rep = $client->__soapCall("Convert", array($param));
        $res = $rep->return;
    } elseif ($action == "comptes") {
        $client = new SoapClient("http://localhost:8085/BanqueWS?wsdl");
        $cptes = $client->__soapCall("comptes", array());
    }
}
?>

<html>
<body>
    <form method="post" action="clientSOAP.php">
        Montant:<input type="text" name="montant" value="<?php echo ($mt) ?>">
        <input type="submit" value="OK" name="action">
        <input type="submit" value="listComptes" name="action">
    </form>
    Rsultat:
    <?php if (isset($res)) {
        echo ($res);
    }
    ?>
    <?php if (isset($cptes)) { ?>
        <table border="1" width="80%">
            <tr>
                <th>CODE</th>
                <th>SOLDE</th>
            </tr>
            <?php foreach ($cptes->return as $cp) { ?>
                <tr>
                    <td><?php echo ($cp->code) ?></td>
                    <td><?php echo ($cp->solde) ?></td>
                </tr>
            <?php } ?>
        </table>
    <?php } ?>
</body>

</html>