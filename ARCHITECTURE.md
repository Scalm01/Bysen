graph TD
    ClientPOST[Client<br/>POST /api/packets<br/>{id,event,minutes,taskID}[]] -->|req.body| Ingest{Array?}
    Ingest -->|No| Err400[400 Error]
    Ingest -->|Yes| Normalize[services/adapter.js<br/>normalizePackets<br/>→ {userId,type,t=minutes*60,taskId}]
    Normalize --> Insert[db.events.insertMany<br/>(VScodeAnalytics)]
    Insert --> RespIngest[{inserted: N}]

    ClientGET[Client<br/>GET /api/analytics] --> Agg[Mongo aggregate<br/>$group user/task time/events<br/>→ user totals[]]
    Agg --> RespAnalytics[JSON users<br/>{_id,tasks[],totalTime,totalEventCount}]

    Startup[server.js<br/>dotenv + MongoClient<br/>MONGO_URI → db] --> Mount[Mount w/ db:<br/>/api/packets=ingest<br/>/api/analytics=analytics]
    Mount --> App[app.js: Express+CORS+JSON<br/>logger middleware<br/>listen PORT=3000]

    classDef client fill:#e1f5fe
    classDef db fill:#f3e5f5
    classDef resp fill:#e8f5e8
    class ClientPOST,ClientGET client
    class Insert,Agg db
    class RespIngest,RespAnalytics resp
