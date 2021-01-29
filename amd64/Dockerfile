# Copyright (c) 2017-2018, Jan Cajthaml <jan.cajthaml@gmail.com>
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

# ---------------------------------------------------------------------------- #

FROM amd64/debian:buster-slim

ENV container docker
ENV LANG C.UTF-8
ENV LC_ALL C
ENV DEBIAN_FRONTEND noninteractive
ENV APT_KEY_DONT_WARN_ON_DANGEROUS_USAGE no
ENV JAVA_HOME /usr/local/openjdk-11
ENV JAVA_VERSION 15.0.2_7
ENV JAVA_DIST_VERSION jdk-15.0.2+7
ENV SCALA_VERSION 2.13.4
ENV SBT_VERSION 1.4.6
ENV LIBRARY_PATH /usr/lib
ENV LD_LIBRARY_PATH /usr/lib
ENV CC gcc
ENV CXX g++
ENV BASHRC /root/.bashrc
ENV SCALA_HOME /root/scala-${SCALA_VERSION}
ENV SBT_HOME /root/sbt-${SBT_VERSION}
ENV PATH="${JAVA_HOME}/bin:${SCALA_HOME}/bin:${SBT_HOME}/bin:${PATH}"

RUN \
    echo "installing debian packages" && \
    apt-get update && \
    apt-get -y install --no-install-recommends \
      ca-certificates \
      wget \
      grc \
      tar \
      libc6 \
      libc6-dev \
      \
      libzmq5>=4.2.1~ \
      libzmq3-dev>=4.2.1~ && \
    \
    apt-get clean autoclean && \
    apt-get autoremove --yes && \
    rm -rf /var/lib/{apt,dpkg,cache,log}/ && \
    :

RUN \
    echo "installing openjdk ${JAVA_DIST_VERSION}" && \
    mkdir -p ${JAVA_HOME} && \
    wget -O - -o /dev/null "https://github.com/AdoptOpenJDK/openjdk15-binaries/releases/download/${JAVA_DIST_VERSION}/OpenJDK15U-jdk_x64_linux_hotspot_${JAVA_VERSION}.tar.gz" | tar xfz - -C /tmp && \
    mv /tmp/${JAVA_DIST_VERSION}/* ${JAVA_HOME}/ && \
    rm -rf /tmp/*

RUN \
    echo "installing scala ${SCALA_VERSION}" && \
    mkdir "${SCALA_HOME}" && \
    wget -O - -o /dev/null "https://downloads.typesafe.com/scala/${SCALA_VERSION}/scala-${SCALA_VERSION}.tgz" | tar xfz - -C "${SCALA_HOME}" --strip-components=1

RUN \
    echo "installing sbt ${SBT_VERSION}" && \
    mkdir "${SBT_HOME}" && \
    wget -O - -o /dev/null "https://github.com/sbt/sbt/releases/download/v${SBT_VERSION}/sbt-${SBT_VERSION}.tgz" | tar xfz - -C "${SBT_HOME}" --strip-components=1

COPY sample /tmp/sample

RUN echo "compiling sample project" && \
    cd /tmp/sample && \
    sbt +test:update && \
    sbt compile && \
    rm -rf /tmp/sample

ENTRYPOINT [ "sbt", "-batch", "--no-color", "-J-Xmx2G", "-JXX:MaxMetaSpaceSize=512M" ]
