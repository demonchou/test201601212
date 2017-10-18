<html style="width:765px;height:667px;margin:0px;paddnig:0px;">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<style>
table{
	  border-bottom:1px solid #666;
		border-right:1px solid #666;
	}
  td{
      padding:7px 0 6px 20px;
      font-size:14px;
      border-top:1px solid #666;
		  border-left:1px solid #666;
  }
  .label{
    background:#fff1f1
  }
</style>
</head>
<body style="width:765px;height:667px;margin:0px;paddnig:0px;font-family:'arial unicode MS','simsun','SimHei,黑体'">
    <div style="overflow:hidden;width:725px;padding:20px;height:627px;font-size:14px;font-family:'arial unicode MS'">
         <p style="text-align:center;font-size:20px;margin-bottom:20px">网易宝有限公司商户专用回单</p>
         <div style="overflow:hidden">
            <div style="float:left;margin-bottom:10px;">回单编号：${platformWithdrawReceiptDto.receiptId!}</div>
            <div style="float:right;">打印时间:${platformWithdrawReceiptDto.createTime!}</div>
         </div> 
         <table cellpadding=0 cellspacing=0 border=0 width="725" height="350"  style="table-layout:fixed">
             <tr>
               <td colspan=24>
                    <div style="float:left">业务类型：${platformWithdrawReceiptDto.businessKey!}</div>
                    <div style="float:right;margin-right:25px;">币种：${platformWithdrawReceiptDto.currencyType!}&nbsp;/&nbsp;单位：${platformWithdrawReceiptDto.currencyUnit!}</div>
               </td>
             </tr>
             <tr>
                <td colspan=3 rowspan=3 class="label">付款方</td>
                <td colspan=21>账户名：${platformWithdrawReceiptDto.outBankAccountName!}</td>
             </tr>
             <tr>
                <td colspan=21>账号：${platformWithdrawReceiptDto.outBankAccount!}</td>
             </tr>
              <tr>
                <td colspan=21>开户机构：${platformWithdrawReceiptDto.outBankName!}</td>
             </tr>
             <tr>
                <td colspan=3 rowspan=3 class="label">收款方</td>
                <td colspan=21>账户名：${platformWithdrawReceiptDto.inBankAccountName!}</td>
             </tr>
             <tr>
                <td colspan=21>账号：${platformWithdrawReceiptDto.inBankAccount!}</td>
             </tr>
              <tr>
                <td colspan=21>开户机构：${platformWithdrawReceiptDto.inBankName!}</td>
             </tr>
             <tr>
                <td colspan=7 class="label">网易支付流水号</td>
                <td colspan=12>${platformWithdrawReceiptDto.withdrawId!}</td>
                <td colspan=5 rowspan=4></td>
             </tr>
             <tr>
                <td colspan=7 class="label">交易状态</td>
                <td colspan=12>${platformWithdrawReceiptDto.realWithdrawState!}</td>
             </tr>
              <tr>
                <td colspan=7 class="label">商户名称（公司名称）</td>
                <td colspan=12>${platformWithdrawReceiptDto.platformName!}（${platformWithdrawReceiptDto.companyName!}）</td>
             </tr>
              <tr>
                <td colspan=7 class="label">记账时间（业务发起时间）</td>
                <td colspan=12>${platformWithdrawReceiptDto.platformWithdrawDate!}</td>
             </tr>
              <tr>
                <td colspan=7 class="label">付款金额</td>
                <td colspan=6>小写：${platformWithdrawReceiptDto.withdrawAmount!}</td>
                <td colspan=11>大写：${platformWithdrawReceiptDto.amountInWords!}</td>
             </tr>
             <tr>
                <td colspan=7 class="label">备注</td>
                <td colspan=17>${platformWithdrawReceiptDto.memo!}</td>
             </tr>
         </table>
         <div style="margin:15px 0 5px;">重要提示：</div>
         <div>1、本回单有任何修改或涂改的，均为无效证明。</div>
         <div style="line-height:30px;">2、本回单仅供参考，如与用户网易宝账户记录不一致的，以网易宝账户记录为准。</div>
         <div>3、本回单仅证明用户在网易支付对应的支付行为。</div>
    </div>
</body>
</html>