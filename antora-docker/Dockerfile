FROM node:24.22.3-bookworm-slim@sha256:873f586f3794f52281bd039bf02c048a0be08fde619338f9941bad1eb6b34530

WORKDIR /opt/antora
COPY package.json package-lock.json ./
RUN npm ci --omit=dev --engine-strict --ignore-scripts

ENV DOCSEARCH_ENABLED=true
ENV DOCSEARCH_ENGINE=lunr

WORKDIR /antora
ENTRYPOINT ["/opt/antora/node_modules/.bin/antora", "--stacktrace", "--generator", "/opt/antora/node_modules/antora-site-generator-lunr"]

LABEL description="Antora documentation image with some extensions"
