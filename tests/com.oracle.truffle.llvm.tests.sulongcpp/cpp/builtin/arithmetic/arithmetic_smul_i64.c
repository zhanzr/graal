#ifndef __clang__
#include <stdbool.h>
bool __builtin_mul_overflow(signed long, signed long, signed long *);
#endif

int main(int argc, const char **argv) {
  signed long res;

  if (__builtin_mul_overflow((signed long)0x0, (signed long)0x0, &res)) {
    return -1;
  }

  if (__builtin_mul_overflow((signed long)0x0, (signed long)0x7FFFFFFFFFFFFFFF, &res)) {
    return -1;
  }

  if (__builtin_mul_overflow((signed long)0x0, (signed long)0x8000000000000000, &res)) {
    return -1;
  }

  if (__builtin_mul_overflow((signed long)0x1, (signed long)0x7FFFFFFFFFFFFFFF, &res)) {
    return -1;
  }

  if (__builtin_mul_overflow((signed long)0x1, (signed long)0x8000000000000000, &res)) {
    return -1;
  }

  if (__builtin_mul_overflow((signed long)0x2, (signed long)0x3FFFFFFFFFFFFFFF, &res)) {
    return -1;
  }

  if (__builtin_mul_overflow((signed long)0x2, (signed long)0xC000000000000000, &res)) {
    return -1;
  }

  if (!__builtin_mul_overflow((signed long)0x2, (signed long)0x7FFFFFFFFFFFFFFF, &res)) {
    return -1;
  }

  if (!__builtin_mul_overflow((signed long)0x2, (signed long)0x8000000000000000, &res)) {
    return -1;
  }

  if (__builtin_mul_overflow((signed long)0x0FFFFFFFFFFFFFFF, (signed long)0x8, &res)) {
    return -1;
  }

  if (!__builtin_mul_overflow((signed long)0x1000000000000000, (signed long)0x8, &res)) {
    return -1;
  }

  if (__builtin_mul_overflow((signed long)0x7FFFFFFFFFFFFFFF, (signed long)0x0, &res)) {
    return -1;
  }

  if (__builtin_mul_overflow((signed long)0x7FFFFFFFFFFFFFFF, (signed long)0x1, &res)) {
    return -1;
  }

  if (!__builtin_mul_overflow((signed long)0x7FFFFFFFFFFFFFFF, (signed long)0x2, &res)) {
    return -1;
  }

  if (!__builtin_mul_overflow((signed long)0x7FFFFFFFFFFFFFFF, (signed long)0x7FFFFFFFFFFFFFFF, &res)) {
    return -1;
  }

  if (!__builtin_mul_overflow((signed long)0x7FFFFFFFFFFFFFFF, (signed long)0x8000000000000000, &res)) {
    return -1;
  }

  if (__builtin_mul_overflow((signed long)0x8000000000000000, (signed long)0x0, &res)) {
    return -1;
  }

  if (__builtin_mul_overflow((signed long)0x8000000000000000, (signed long)0x1, &res)) {
    return -1;
  }

  if (!__builtin_mul_overflow((signed long)0x8000000000000000, (signed long)0x2, &res)) {
    return -1;
  }

  if (!__builtin_mul_overflow((signed long)0x8000000000000000, (signed long)0x7FFFFFFFFFFFFFFF, &res)) {
    return -1;
  }

  if (!__builtin_mul_overflow((signed long)0x8000000000000000, (signed long)0x8000000000000000, &res)) {
    return -1;
  }

  if (__builtin_mul_overflow((signed long)0xFFFFFFFFFFFFFFFF, (signed long)0x0, &res)) {
    return -1;
  }

  if (__builtin_mul_overflow((signed long)0xFFFFFFFFFFFFFFFF, (signed long)0x1, &res)) {
    return -1;
  }

  if (__builtin_mul_overflow((signed long)0xFFFFFFFFFFFFFFFF, (signed long)0xFFFFFFFFFFFFFFFF, &res)) {
    return -1;
  }

  return 0;
}
