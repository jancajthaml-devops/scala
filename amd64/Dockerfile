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
ENV JAVA_VERSION 8u212b04
ENV JAVA_DIST_VERSION openjdk-8u212-b04
ENV GRAALVM_VERSION 19.2.0
ENV SCALA_VERSION 2.12.8
ENV SBT_VERSION 1.2.8
ENV LIBRARY_PATH /usr/lib
ENV LD_LIBRARY_PATH /usr/lib
ENV CC gcc
ENV CXX g++
ENV BASHRC /root/.bashrc
ENV SCALA_HOME /root/scala-${SCALA_VERSION}
ENV GRAALVM_HOME /root/graalvm-${GRAALVM_VERSION}
ENV SBT_HOME /root/sbt-${SBT_VERSION}
ENV PATH=${JAVA_HOME}/bin:${SCALA_HOME}/bin:${GRAALVM_HOME}/bin:${SBT_HOME}/bin:${PATH}

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
    mkdir ${SCALA_HOME} && \
    curl -fsL "https://downloads.typesafe.com/scala/${SCALA_VERSION}/scala-${SCALA_VERSION}.tgz" | tar xfz - -C ${SCALA_HOME} --strip-components=1 && \
    \
    echo "installing sbt ${SBT_VERSION}" && \
    mkdir ${SBT_HOME} && \
    curl -fsL "https://sbt-downloads.cdnedge.bluemix.net/releases/v${SBT_VERSION}/sbt-$SBT_VERSION.tgz" | tar xfz - -C ${SBT_HOME} --strip-components=1 && \
    \
    echo "installing grallVM ${GRAALVM_VERSION}" && \
    mkdir ${GRAALVM_HOME} && \
    curl -fsL "https://github.com/oracle/graal/releases/download/vm-${GRAALVM_VERSION}/graalvm-ce-linux-amd64-${GRAALVM_VERSION}.tar.gz" | tar -xvzf - -C ${GRAALVM_HOME} --strip-components=1 && \
    \
    rm -rf /var/lib/apt/lists/* /tmp/*

COPY --from=jancajthaml/jq /usr/local/bin/jq /usr/bin/jq
COPY --from=library/docker:18.06 /usr/local/bin/docker /usr/bin/docker
