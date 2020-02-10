FROM registry.access.redhat.com/openshift3/jenkins-agent-nodejs-8-rhel7
USER root

RUN yum repolist > /dev/null && \
    yum install -y yum-utils && \
    wget https://dl.google.com/linux/direct/google-chrome-stable_current_x86_64.rpm && \
    yum repolist all && \
    yum -y install redhat-lsb libXScrnSaver && \
    yum -y localinstall google-chrome-stable_current_x86_64.rpm

USER 1001
