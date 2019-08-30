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

FROM amd64/debian:stretch-slim

ENV container docker
ENV LANG C.UTF-8
ENV LC_ALL C
ENV DEBIAN_FRONTEND noninteractive
ENV APT_KEY_DONT_WARN_ON_DANGEROUS_USAGE=no
ENV JAVA_HOME /usr/local/openjdk-8
ENV GRAAL_VERSION 19.2.0
ENV GRAAL_HOME /opt/graalvm-ce-${GRAAL_VERSION}
ENV PATH $JAVA_HOME/bin:$PATH
ENV JAVA_VERSION 8u212b04
ENV JAVA_DIST_VERSION openjdk-8u212-b04
ENV SCALA_VERSION 2.13.0
ENV SBT_VERSION 1.2.8
ENV LIBRARY_PATH /usr/lib
ENV LD_LIBRARY_PATH /usr/lib
ENV CC gcc
ENV CXX g++

RUN apt-get update && \
    \
    echo "installing essentials" && \
    apt-get install -y --no-install-recommends \
      apt-utils \
      curl \
      ca-certificates \
      && \
    \
    echo "installing debian packages" && \
    apt-get -y install --no-install-recommends \
      git \
      grc \
      tar \
      cmake \
      make \
      patch \
      python \
      debhelper \
      fontconfig \
      libfreetype6 \
      config-package-dev \
      fakeroot \
      pkg-config \
      lintian \
      libsystemd-dev \
      gcc \
      g++ \
      libc6 \
      libc6-dev \
      \
      libzmq5>=4.2.1~ \
      libzmq3-dev>=4.2.1~ \
      \
      && \
    \
    echo "installing openjdk ${JAVA_VERSION}" && \
    mkdir -p ${JAVA_HOME} && \
    curl -fsL "https://github.com/AdoptOpenJDK/openjdk8-upstream-binaries/releases/download/jdk8u212-b04/OpenJDK8U-x64_linux_${JAVA_VERSION}.tar.gz" | tar xfz - -C /tmp && \
    mv /tmp/${JAVA_DIST_VERSION}/* ${JAVA_HOME}/ && \
    rm -rf /tmp/${JAVA_DIST_VERSION} && \
    \
    echo "installing scala ${SCALA_VERSION}" && \
    curl -fsL "https://downloads.typesafe.com/scala/${SCALA_VERSION}/scala-${SCALA_VERSION}.tgz" | tar xfz - -C /root/ && \
      echo >> /root/.bashrc && \
      echo "export PATH=~/scala-${SCALA_VERSION}/bin:${PATH}" >> /root/.bashrc && \
    \
    echo "installing sbt ${SBT_VERSION}" && \
    curl -fsL "https://dl.bintray.com/sbt/debian/sbt-${SBT_VERSION}.deb" -o /tmp/sbt-${SBT_VERSION}.deb && \
      dpkg -i /tmp/sbt-${SBT_VERSION}.deb && \
      rm -f /tmp/sbt-${SBT_VERSION}.deb && \
      sbt sbtVersion && \
      echo "export PATH=/root/scala-${SCALA_VERSION}/bin:${PATH}" >> /root/.bashrc && \
    \
    echo "installing grallVM ${GRAAL_VERSION}" && \
    curl -fsL "https://github.com/oracle/graal/releases/download/vm-${GRAAL_VERSION}/graalvm-ce-linux-amd64-${GRAAL_VERSION}.tar.gz" | tar xfz - -C /opt && \
    \
    rm -rf /var/lib/apt/lists/* /tmp/*

ENV JAVA_HOME=${GRAAL_HOME}
ENV PATH=${PATH}:${JAVA_HOME}/bin

COPY --from=jancajthaml/jq /usr/local/bin/jq /usr/bin/jq
COPY --from=library/docker:18.06 /usr/local/bin/docker /usr/bin/docker
