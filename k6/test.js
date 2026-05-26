import http from 'k6/http';
import { sleep, check } from 'k6';

export let options = {
  vus: 10,
  duration: '30s',
  thresholds: {
    http_req_failed: ['rate<0.01'],
    http_req_duration: ['p(95)<500'],
  },
};

export default function () {
  let res = http.get('http://localhost:8080');
  check(res, { 'status 200': (r) => r.status === 200 });
  sleep(1);
}