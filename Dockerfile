# syntax=docker/dockerfile:1
FROM ubuntu:latest

ARG USERNAME=mendirl
ARG USER_UID=1000
ARG USER_GID=$USER_UID

RUN groupadd --gid $USER_GID $USERNAME \
        && useradd --uid $USER_UID --gid $USER_GID -m $USERNAME \

#RUN groupmod --gid $USER_GID $USERNAME \
#    && usermod --uid $USER_UID --gid $USER_GID $USERNAME \
#    && chown -R $USER_UID:$USER_GID /home/$USERNAME

COPY --chmod=755 <<EOF /app/run.sh
#!/bin/sh
while true; do
  echo -ne "The time is now $(date +%T)\\r"
  ls -ails
  sleep 1
done
EOF

ENTRYPOINT /app/run.sh
