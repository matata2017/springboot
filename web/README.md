#nginx配置

worker_processes  1;

#pid        logs/nginx.pid;

events {
    worker_connections  1024;
}


http {
    include       mime.types;
    default_type  application/octet-stream;
    sendfile        on;

    server {
		index index.dhtml;
		
        	listen       84;
       	 	server_name  localhost;
		
		#gzip on;
		#gzip_min_length 1k;
		#gzip_buffers 4 16k;
		#gzip_http_version 1.0;
		#gzip_comp_level 2;
		#gzip_types text/plain application/javascript application/x-javascript text/css application/xml application/json text/javascript application/x-httpd-php image/png;
		#gzip_vary off;
		#gzip_disable "MSIE [1-6]\.";

        	#charset koi8-r;

        	#access_log  logs/host.access.log  main;
		
		#proxy_redirect ~http://127.0.0.1:[0-9]+/(.*) /$1;
		

		location /portfolio {
			proxy_pass   http://127.0.0.1:8081/portfolio;
			proxy_set_header   Host             $host:$server_port;
        		proxy_set_header   X-Real-IP        $remote_addr;
        		proxy_set_header   X-Forwarded-For  $proxy_add_x_forwarded_for;
        		proxy_http_version 1.1;
        		proxy_set_header Upgrade $http_upgrade;
        		proxy_set_header Connection "upgrade";
		}

		location / {
			proxy_pass   http://127.0.0.1:4200;
			proxy_set_header   Host             $host:$server_port;
        		proxy_set_header   X-Real-IP        $remote_addr;
        		proxy_set_header   X-Forwarded-For  $proxy_add_x_forwarded_for;
        		proxy_http_version 1.1;
        		proxy_set_header Upgrade $http_upgrade;
        		proxy_set_header Connection "upgrade";

			#try_files $uri /manager.html = 404;
		}
		location /rest/ {
			proxy_pass   http://127.0.0.1:8081;
			proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
			        		proxy_http_version 1.1;
        		proxy_set_header Upgrade $http_upgrade;
        		proxy_set_header Connection "upgrade";
		}
		location /app/ {
			proxy_pass   http://127.0.0.1:8081;
			proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
			        		proxy_http_version 1.1;
        		proxy_set_header Upgrade $http_upgrade;
        		proxy_set_header Connection "upgrade";
		}
		
	}

}
