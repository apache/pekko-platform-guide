#!/usr/bin/env bash
if [ -z ${SCP_SECRET} ]; then
  echo "No SCP_SECRET found."
  exit 1;
fi
make build-wip
eval "$(ssh-agent -s)"
echo "${SCP_SECRET}" > /tmp/id_rsa
chmod 600 /tmp/id_rsa
# To be determined: Set up a test VM TEST_VM_HOSTNAME to deploy site into 
scp -i /tmp/id_rsa -o UserKnownHostsFile=.travis/known_hosts -r target/* pekkorepo@<TEST_VM_HOSTNAME>:pekko.apache.org/docs/pekko-platform-guide/wip/
