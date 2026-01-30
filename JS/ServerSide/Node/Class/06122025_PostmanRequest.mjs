import request from 'postman-request';
const{log}=console;
// log(all)

const options = {
  url: 'https://www.google.com',
  method: 'GET'
};

request(options, (err, res, body) => {
  if (err) {
    console.error('Request failed:', err);
    return;
  }

  console.log('Status:', res.statusCode);
  console.log('Body:', body);
});