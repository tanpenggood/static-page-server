## static-page-server

A simple static page server.

It can easier map a folder to network resource that can be accessed by HTTP.

## Resource Mapping

`--static-page.resource-mappings.xx=/xxx/`

**Single Folder Mapping**

```
--static-page.resource-mappings.cq=D:/file/201228-201231/cq-api/
```

**Multiple Folder Mapping**

```
--static-page.resource-mappings.bj=D:/file/201228-201231/bj-api/
--static-page.resource-mappings.cq=D:/file/201228-201231/cq-api/
--static-page.resource-mappings.sc=D:/file/201228-201231/sc-api/
```

**Notes**

1.  The mapping path must to be absolute.

2. The mapping path must to end with `/`.

## Usage

1. set config for resource mapping, if you need.
2. run `com.itplh.page.StaticPageApplication`
3. access `http://localhost:8080`

## Boot Script

```bash
set -e
env=prod
log_name=$env-static-page-server.log
echo '' > $log_name
nohup java -jar -Xms512m -Xmx512m -XX:+UseConcMarkSweepGC \
-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./ \
static-page-server.jar --spring.profiles.active=$env \
--server.port=8080 \
--static-page.resource-mappings.cq=/home/data/file/cq-api/ \
--static-page.resource-mappings.bj=/home/data/file/bj-api/ \
> $log_name 2>&1 &
```

