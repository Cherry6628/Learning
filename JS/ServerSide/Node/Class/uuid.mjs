import {
    NIL, MAX, parse, stringify, v1,
    v1ToV6, v3, v4, v5, v6, v6ToV1,
    v7, validate, version, //v8
} from 'uuid';

const { log } = console;


// ---------------------------------------------------------
// Purpose summary
//   v4     : Random + safe
//   v5     : Deterministic name-based
//   v6/v7  : Time-sortable
//   v1/v3  : Legacy

// ---------------------------------------------------------
// Parameters Summary (may not be mentioned below in some versions)
//   v1/v6  : timestamp-based
//   v4     : random
//   v3     : MD5(name+namespace)             name and namespace are compulsory
//   v5     : SHA-1(name+namespace)           name and namespace are compulsory
//   v7     : Unix time + randomness




// ---------------------------------------------------------
// NIL UUID
// Type: String
// Description: A UUID that is all zeros.
// Returns: "00000000-0000-0000-0000-000000000000"
log("NIL:", NIL);

// ---------------------------------------------------------
// MAX UUID
// Type: String
// Description: Largest possible UUID (all bits = 1)
log("MAX:", MAX);

// ---------------------------------------------------------
// parse(uuidString)
// Params: uuidString: string
// Returns: Uint8Array(16)
// Throws: TypeError if input is not a valid UUID
let validUUID = "6762bf15-2ae4-4212-a067-1197982600a4";
log("parse():", parse(validUUID));

// ---------------------------------------------------------
// stringify(uint8Array, offset?)
// Params:
//   uint8Array: Array | Uint8Array of length >= 16
//   offset (optional): number index to start reading
// Returns: UUID string
// Throws: TypeError if invalid buffer
log("stringify():", stringify([
  103, 103, 98, 191, 21, 42, 228,
  66, 18, 160, 103, 17, 151,
  152, 38, 0, 164
], 1));

// ---------------------------------------------------------
// v1(options?, buffer?)
// Type: UUID v1 generator
// Params (optional):
//   options.node: Uint8Array[6] - simulated MAC address
//   options.clockseq: number 0–16383
//   options.msecs: timestamp (ms)
//   options.nsecs: number 0–10000
//   options.random: number[16]
//   buffer: Uint8Array(16) to write binary UUID
// Returns: UUID string OR buffer
const options = {
  node: Uint8Array.of(0x01, 0x23, 0x45, 0x67, 0x89, 0xab),
  clockseq: 0x1234,
  msecs: new Date('2011-11-01').getTime(),
  nsecs: 5678,
  random: [0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0]
};

let buffer = new Uint8Array(16);
log("v1():", v1(options, buffer));
log("v1 buffer:", buffer);

// ---------------------------------------------------------
// v1ToV6(buffer)
// Params: buffer = Uint8Array(16) (v1 binary)
// Returns: UUIDv6 string
log("v1ToV6():", v1ToV6(buffer));

// ---------------------------------------------------------
// v3(name, namespace)
// Deterministic UUID using MD5
// Params:
//   name: string
//   namespace: UUID string (must be v3.DNS or v3.URL or custom)
// Throws: Error if namespace missing
log("v3():", v3("www.google.com", v3.DNS));
log("v3.URL:", v3.URL);

// ---------------------------------------------------------
// v4()
// Random UUID
log("v4():", v4());

// ---------------------------------------------------------
// v5(name, namespace)
// Deterministic UUID using SHA-1
log("v5():", v5('https://www.w3.org/', v5.URL));

// ---------------------------------------------------------
// v6()
// Ordered-time UUID (improved v1)
log("v6():", v6());

// ---------------------------------------------------------
// v6ToV1(uuidV6)
// Converts v6 back to v1
log("v6ToV1():", v6ToV1('1ef22c49-2f62-6d9e-97e9-325096b39f47'));

// ---------------------------------------------------------
// v7()
// High-performance time-ordered UUID (based on Unix time)
log("v7():", v7());

// ---------------------------------------------------------
// validate(uuid)
// Returns: boolean
log("validate() valid:", validate('6ec0bd7f-11c0-43da-975e-2a9999ebae0b'));
log("validate() invalid:", validate('not a UUID'));

// ---------------------------------------------------------
// version(uuid)
// Returns: number (1,3,4,5,6,7,...)
log("version():", version('45637ec4-c85f-11ea-87d0-0242ac130003'));
log("version():", version('6ec0bd7f-11c0-43da-975e-2a9999ebae0b'));
