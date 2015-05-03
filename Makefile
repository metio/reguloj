all: help

help:
	@echo ""
	@echo "-- Help Menu"
	@echo ""
	@echo "   1. make sonar-analysis        - perform sonar analysis"

sonar-analysis:
	@mvn sonar:sonar -Dsonar.host.url=http://localhost:58200 -Dsonar.jdbc.url=jdbc:postgresql://localhost:58300/sonar

