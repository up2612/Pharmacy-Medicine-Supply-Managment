<%@ include file="common/header.jspf"%>


<article>
	<div class="container">
		<h1 class="article-heading text-center m-4">Medicine Demand</h1>
			<table class="table table-striped table-bordered bg-light rounded">
				<tr class="text-center bg-secondary text-white" style="font-size: 1.3em;">
				
					<th>Medicine</th>
					<th>Demand Count</th>
					<th>Order TimeStamp</th>
				</tr>
			</thead>

			<c:forEach items="${medicineDemandList}" var="medicineDemand">
				<tr>
					<td class="col-right">${medicineDemand.medicinename}</td>
					<td class="col-right">${medicineDemand.demandcount}</td>
					<td class="col-right">${medicineDemand.tranDate}</td>
				</tr>

			</c:forEach>

		</table>
	</div>
</article>


<%@ include file="common/footer.jspf"%>