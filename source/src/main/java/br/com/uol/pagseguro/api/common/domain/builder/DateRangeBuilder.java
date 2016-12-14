/*
 * 2007-2016 [PagSeguro Internet Ltda.]
 *
 * NOTICE OF LICENSE
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright: 2007-2016 PagSeguro Internet Ltda.
 * Licence: http://www.apache.org/licenses/LICENSE-2.0
 */
package br.com.uol.pagseguro.api.common.domain.builder;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import br.com.uol.pagseguro.api.common.domain.DateRange;
import br.com.uol.pagseguro.api.utils.Builder;

/**
 * Builder for date range
 *
 * @author PagSeguro Internet Ltda.
 */
public final class DateRangeBuilder implements Builder<DateRange> {

  private Date from;

  private Date to;

  /**
   * Set between range interval
   *
   * @param from Date From
   * @param to   Date to
   * @return Builder for date range
   * @see DateRange#getFrom()
   * @see DateRange#getTo()
   */
  public DateRangeBuilder between(Date from, Date to) {
    from(from);
    to(to);
    return this;
  }

  /**
   * Convert in millis
   *
   * @param duration Duration
   * @param timeUnit Time unit
   * @return Millis
   */
  private long toMillis(int duration, TimeUnit timeUnit) {
    return TimeUnit.MILLISECONDS.convert(duration, timeUnit);
  }

  /**
   * Set after date
   *
   * @param duration Duration
   * @param timeUnit Time unit
   * @return Builder for date range
   * @see DateRange#getFrom()
   */
  public DateRangeBuilder after(int duration, TimeUnit timeUnit) {
    final Calendar cal = Calendar.getInstance();
    cal.add(Calendar.MILLISECOND, (int) -toMillis(duration, timeUnit));
    return from(cal);
  }

  /**
   * Set until date
   *
   * @param duration Duration
   * @param timeUnit Time unit
   * @return Builder for date range
   * @see DateRange#getTo()
   */
  public DateRangeBuilder until(int duration, TimeUnit timeUnit) {
    final Calendar cal = Calendar.getInstance();
    cal.add(Calendar.MILLISECOND, (int) toMillis(duration, timeUnit));
    return to(cal);
  }

  /**
   * Set from date
   *
   * @param date Date
   * @return Builder for date range
   * @see DateRange#getFrom()
   */
  public DateRangeBuilder from(Date date) {
    this.from = date;
    return this;
  }

  /**
   * Set from date
   *
   * @param calendar Calendar
   * @return Builder for date range
   * @see DateRange#getFrom()
   */
  public DateRangeBuilder from(Calendar calendar) {
    return from(calendar.getTime());
  }

  /**
   * Set to date
   *
   * @param date Date
   * @return Builder for date range
   * @see DateRange#getTo()
   */
  public DateRangeBuilder to(Date date) {
    this.to = date;
    return this;
  }

  /**
   * Set to date
   *
   * @param calendar Calendar
   * @return Builder for date range
   * @see DateRange#getTo()
   */
  public DateRangeBuilder to(Calendar calendar) {
    return to(calendar.getTime());
  }

  /**
   * Build the Date Range
   *
   * @return Interface for Date Range
   * @see DateRange
   */
  @Override
  public DateRange build() {
    return new SimpleDateRange(this);
  }

  /**
   * Implementation of {@code DateRange}
   */
  private static class SimpleDateRange implements DateRange {

    private final DateRangeBuilder dateRangeBuilder;

    public SimpleDateRange(DateRangeBuilder dateRangeBuilder) {
      this.dateRangeBuilder = dateRangeBuilder;
    }

    @Override
    public Date getFrom() {
      return dateRangeBuilder.from;
    }

    @Override
    public Date getTo() {
      return dateRangeBuilder.to;
    }

  }

}
