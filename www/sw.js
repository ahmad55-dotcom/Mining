const V='m1';self.addEventListener('install',e=>self.skipWaiting());
self.addEventListener('activate',e=>e.waitUntil(clients.claim()));
self.addEventListener('fetch',e=>{const u=e.request.url;if(e.request.method!='GET'||u.includes('config.json')||u.includes('api.'))return;
e.respondWith(caches.match(e.request).then(h=>h||fetch(e.request).then(r=>{if(r.ok&&u.startsWith(location.origin)&&!u.includes('.mp3')){const c=r.clone();caches.open(V).then(x=>x.put(e.request,c))}return r}).catch(()=>caches.match('index.html'))))});
