# Guia de Configuração: JMeter, InfluxDB e Grafana

Este guia contém a configuração para visualizar métricas do JMeter no Grafana.

## Passos para Funcionar

### 1. Subir a Stack Corretamente
```bash
docker-compose up -d
```

### 2. Configuração do Data Source no Grafana (O PONTO CHAVE)
Ao configurar o InfluxDB no Grafana (`http://localhost:3000`):
*   **URL:** Use `http://influxdb:8086` (O Grafana está dentro da rede do Docker, então ele usa o nome do serviço, não `localhost`).
*   **Access:** `Server (Default)`
*   **Database:** `jmeter`
*   **HTTP Method:** `GET`
*   **Min time interval:** `5s`

### 3. Executar o Teste no JMeter
Abra o `load_test_influxdb.jmx` e clique no Play. O teste rodará por 60 segundos.

## Dica do Dashboard
Ao importar o Dashboard `5496`, certifique-se de selecionar o Data Source correto no dropdown que aparece durante a importação. No dashboard, mude o tempo para **"Last 5 minutes"** e o refresh para **"5s"**.
