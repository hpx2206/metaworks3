	<frameset cols="210,*" border=1 bordercolor=blue>
		<frameset rows=1,2 border=1 bordercolor=blue>
			<frame src="processDefinitionList.jsp" name=definitionList frameborder=0>
			<frame src="procins_search_menu.jsp?target=All" name="instancelist" frameborder=0>
			<!--frame src="processArchiveList.jsp" name="archivelist"-->
		</frameset>
		<frame src="processInstanceListDetail.jsp" name="innerworkarea" frameborder=0>
	</frameset>
	