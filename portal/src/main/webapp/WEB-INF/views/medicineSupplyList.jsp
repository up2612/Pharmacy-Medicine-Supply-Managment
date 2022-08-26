<%@ include file="common/header.jspf"%>


<article>
	<div class="container">
		<h1 class="article-heading text-center m-4">Order Placed</h1>
			<table class="table table-striped table-bordered bg-light rounded">
				<tr class="text-center bg-secondary text-white" style="font-size: 1.3em;">
				
				
					<th>Pharmacy Name</th>
					<th>Medicine Name</th>
					<th>Supply Count</th>
					<th>Order Timestamp</th>
				</tr>
			</thead>
			<c:forEach items="${medicineSupplyList}" var="medicineSupply">
				<tr>
					<td class="col-right">${medicineSupply.pharmacyname}</td>
					<td class="col-right">${medicineSupply.medicinename}</td>
					<td class="col-right">${medicineSupply.supplycount}</td>
					<td class="col-right">${medicineSupply.tranDate}</td>
				</tr>

			</c:forEach>

		</table>
	</div>

</article>


<%@ include file="common/footer.jspf"%>