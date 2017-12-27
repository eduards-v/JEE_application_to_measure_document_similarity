<%@ include file="includes/header.jsp" %>

<div class="animated bounceInDown" style="font-size:32pt; font-family:arial; color:#990000; font-weight:bold">Document Comparison Service</div>

</p>&nbsp;</p>&nbsp;</p>

<table width="600" cellspacing="0" cellpadding="7" border="0">
	<tr>
		<td valign="top">

			<form method="post" action="processing">
				<fieldset>
					<legend><h3>Specify Details</h3></legend>

					<b>Document Title :</b><br>
					<input name="txtTitle" type="text" size="50"/>
					<p/>
					<input type="file" name="txtDocument"/>
					<input type="submit" value="Compare Document">
				</fieldset>
			</form>	

		</td>
	</tr>
</table>
<%@ include file="includes/footer.jsp" %>

