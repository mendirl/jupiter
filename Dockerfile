# syntax=docker/dockerfile:1
FROM busybox:latest

RUN groupadd --gid 1000 mendirl \
    && useradd --uid 1000 --gid 1000 -m mendirl


COPY --chmod=755 <<EOF /app/run.sh
#!/bin/sh
while true; do
  echo -ne "The time is now $(date +%T)\\r"
  ls -ails
  sleep 1
done
EOF

ENTRYPOINT /app/run.sh
