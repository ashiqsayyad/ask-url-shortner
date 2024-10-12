{{/*
A helper template to manage release name and other reusable logic for the ask-url-shortner-app.
*/}}

{{- define "ask-url-shortner-app.releaseName" -}}
{{- if .Release.Name -}}
  {{ .Release.Name }}
{{- else -}}
  default-release-name
{{- end -}}
{{- end -}}

{{/*
A helper to construct the full name for resources.
*/}}
{{- define "ask-url-shortner-app.fullname" -}}
{{- printf "%s-%s" (include "ask-url-shortner-app.releaseName" .) .Chart.Name | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{/*
A helper to get the chart name.
*/}}
{{- define "ask-url-shortner-app.chart" -}}
{{ .Chart.Name }}
{{- end -}}

{{/*
A helper to get the chart version.
*/}}
{{- define "ask-url-shortner-app.version" -}}
{{ .Chart.Version }}
{{- end -}}

{{- define "ask-url-shortner-app.name" -}}
{{ .Chart.Name }}
{{- end -}}